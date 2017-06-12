package neuralnetwork.factories;

import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.Neuron;
import neuralnetwork.implementation.Kohonen;
import neuralnetwork.implementation.neuron.BiasNeuron;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to create various neural networks.
 */
public class NeuralNetworkFactory {

    /**
     * Creates and returns a new instance of Kohonen network
     * @param inputNeuronsCount number of input neurons
     * @param outputNeuronsCount number of output neurons
     * @return instance of Kohonen network
     */
    public static Kohonen createKohonen(int inputNeuronsCount, int outputNeuronsCount) {
        Kohonen nnet = new Kohonen(inputNeuronsCount, outputNeuronsCount);
        return nnet;
    }

    /**
     * Sets default input and output neurons for network (first layer as input,
     * last as output)
     */
    public static void setDefaultIO(NeuralNetwork nnet) {
        ArrayList<Neuron> inputNeuronsList = new ArrayList<>();
        Layer firstLayer = nnet.getLayerAt(0);
        for (Neuron neuron : firstLayer.getNeurons() ) {
            if (!(neuron instanceof BiasNeuron)) {  // dont set input to bias neurons
                inputNeuronsList.add(neuron);
            }
        }

        List<Neuron> outputNeurons = ((Layer) nnet.getLayerAt(nnet.getLayersCount()-1)).getNeurons();

        nnet.setInputNeurons(inputNeuronsList);
        nnet.setOutputNeurons(outputNeurons);
    }

}

