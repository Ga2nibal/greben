package neuralnetwork.implementation.leaning;


import neuralnetwork.base.Connection;
import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.Neuron;
import neuralnetwork.base.data.DataSet;
import neuralnetwork.base.data.DataSetRow;
import neuralnetwork.base.events.LearningEvent;
import neuralnetwork.base.learning.LearningRule;

import java.util.Iterator;

/**
 * Learning algorithm for Kohonen network.
 */
public class KohonenLearning extends LearningRule {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    double learningRate = 0.9d;
    int[] iterations = { 100, 0 };
    double[] decStep = new double[2];
    int mapSize = 0;
    int[] nR = { 1, 1 }; // neighborhood radius
    int currentIteration;


    public KohonenLearning() {
        super();
    }

    @Override
    public void learn(DataSet trainingSet) {

        for (int phase = 0; phase < 2; phase++) {
            for (int k = 0; k < iterations[phase]; k++) {
                Iterator<DataSetRow> iterator = trainingSet.iterator();
                while (iterator.hasNext() && !isStopped()) {
                    DataSetRow trainingSetRow = iterator.next();
                    learnPattern(trainingSetRow, nR[phase]);
                } // while
                currentIteration = k;
                fireLearningEvent(new LearningEvent(this, LearningEvent.Type.EPOCH_ENDED));
                if (isStopped()) return;
            } // for k
            learningRate = learningRate * 0.5;
        } // for phase
    }

    private void learnPattern(DataSetRow dataSetRow, int neighborhood) {
        neuralNetwork.setInput(dataSetRow.getInput());
        neuralNetwork.calculate();
        Neuron winner = getClosestNeuron();
        if (winner.getOutput() == 0)
            return; // ako je vec istrenirana jedna celija, izadji

        Layer mapLayer = neuralNetwork.getLayerAt(1);
        int winnerIdx = mapLayer.indexOf(winner);
        adjustCellWeights(winner, 0);

        int cellNum = mapLayer.getNeuronsCount();
        for (int p = 0; p < cellNum; p++) {
            if (p == winnerIdx)
                continue;
            if (isNeighbor(winnerIdx, p, neighborhood)) {
                Neuron cell = mapLayer.getNeuronAt(p);
                adjustCellWeights(cell, 1);
            } // if
        } // for

    }

    // get unit with closetst weight vector
    private Neuron getClosestNeuron() {
        Neuron winner = new Neuron();
        double minOutput = 100;
        for(Neuron n : this.neuralNetwork.getLayerAt(1).getNeurons() ) {
            double out = n.getOutput();
            if (out < minOutput) {
                minOutput = out;
                winner = n;
            } // if
        } // while
        return winner;
    }

    private void adjustCellWeights(Neuron cell, int r) {
        for(Connection conn : cell.getInputConnections()) {
            double dWeight = (learningRate / (r + 1))
                    * (conn.getInput() - conn.getWeight().getValue());
            conn.getWeight().inc(dWeight);
        }
    }

    private boolean isNeighbor(int i, int j, int n) {
        // i - centralna celija
        // n - velicina susedstva
        // j - celija za proveru
        n = 1;
        int d = mapSize;

        // if (j<(i-n*d-n)||(j>(i+n*d+n))) return false;

        int rt = n; // broj celija ka gore
        while ((i - rt * d) < 0) {
            rt--;
        }

        int rb = n; // broj celija ka dole
        while ((i + rb * d) > (d * d - 1)) {
            rb--;
        }

        for (int g = -rt; g <= rb; g++) {
            int rl = n; // broj celija u levu stranu
            int rlMod = (i - rl) % d;
            int i_mod = i % d;
            while (rlMod > i_mod) {
                rl--;
                rlMod = (i - rl) % d;
            }

            int rd = n; // broj celija u desnu stranu
            int rdMod = (i + rd) % d;
            while (rdMod < i_mod) {
                rd--;
                rdMod = (i + rd) % d;
            }

            if ((j >= (i + g * d - rl)) && (j <= (i + g * d + rd)))
                return true;
            // else if (j<(i+g*d-rl)) return false;
        } // for
        return false;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setIterations(int Iphase, int IIphase) {
        this.iterations[0] = Iphase;
        this.iterations[1] = IIphase;
    }

    public int getIteration() {
        return currentIteration;
    }

    public int getMapSize() {
        return mapSize;
    }

    @Override
    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        super.setNeuralNetwork(neuralNetwork);
        int neuronsNum = neuralNetwork.getLayerAt(1).getNeuronsCount();
        mapSize = (int) Math.sqrt(neuronsNum);
    }

}
