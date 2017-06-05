package neuralnetwork2.nnet.neuron;

import neuralnetwork2.Neuron;
import neuralnetwork2.input.InputFunction;
import neuralnetwork2.transfer.TransferFunction;

/**
 * Provides behaviour specific for neurons which act as input and the output
 * neurons within the same layer. For example in Hopfield network and BAM.
 */
public class InputOutputNeuron extends Neuron {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Flag which is set true if neuron external input is set
     */
    private boolean externalInputSet;

    /**
     * Bias value for this neuron
     */
    private double bias = 0;

    /**
     * Creates an instance of neuron for Hopfield network
     */
    public InputOutputNeuron() {
        super();
    }

    /**
     * Creates an instance of neuron for Hopfield network with specified input
     * and transfer functions
     * @param inFunc neuron input function
     * @param transFunc neuron transfer function
     */
    public InputOutputNeuron(InputFunction inFunc, TransferFunction transFunc) {
        super(inFunc, transFunc);
    }

    /**
     * Sets total net input for this cell
     *
     * @param input
     *            input value
     */
    @Override
    public void setInput(double input) {
        this.totalInput = input;
        this.externalInputSet = true;
    }

    /**
     * Returns bias value for this neuron
     * @return bias value for this neuron
     */
    public double getBias() {
        return bias;
    }

    /**
     * Sets bias value for this neuron
     * @param bias bias value for this neuron
     */
    public void setBias(double bias) {
        this.bias = bias;
    }

    /**
     * Calculates neuron output
     */
    @Override
    public void calculate() {

        if (!externalInputSet) { // ako ulaz nije setovan spolja
            if (this.hasInputConnections()) // bias neuroni ne racunaju ulaz iz mreze jer
                // nemaju ulaze
                totalInput = inputFunction.getOutput(this.inputConnections);
        }

        // calculqate cell output
        this.output = transferFunction.getOutput(this.totalInput + bias); // izracunaj
        // izlaz

        if (externalInputSet) { // ulaz setovan 'spolja' vazi samo za jedno izracunavanje
            externalInputSet = false;
            totalInput = 0;
        }
    }

}

