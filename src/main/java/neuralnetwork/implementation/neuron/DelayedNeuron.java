package neuralnetwork.implementation.neuron;

import neuralnetwork.base.Neuron;
import neuralnetwork.base.input.InputFunction;
import neuralnetwork.base.transfer.TransferFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides behaviour for neurons with delayed output.
 */
public class DelayedNeuron extends Neuron {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Output history for this neuron
     */
    protected transient List<Double> outputHistory;

    /**
     * Creates an instance of neuron which can delay output
     * @param inputFunction neuron input function
     * @param transferFunction neuron transfer function
     */
    public DelayedNeuron(InputFunction inputFunction,
                         TransferFunction transferFunction) {
        super(inputFunction, transferFunction);
        outputHistory = new ArrayList<Double>(5); // default delay buffer size is 5
        outputHistory.add(new Double(0));
    }

    @Override
    public void calculate() {
        super.calculate();
        outputHistory.add(0, new Double(this.output));
        if (outputHistory.size() > 5)
            outputHistory.remove(5);
    }

    /**
     * Returns neuron output with the specified delay
     * @param delay output delay
     * @return neuron output at (t-delay) moment
     */
    public double getOutput(int delay) {
        return outputHistory.get(delay).doubleValue();
    }


    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        outputHistory = new ArrayList<>(5);
    }

}

