package neuralnetwork2.nnet.neuron;

import neuralnetwork2.Neuron;
import neuralnetwork2.input.InputFunction;
import neuralnetwork2.transfer.TransferFunction;

/**
 * Provides behaviour for neurons with threshold.
 */
public class ThresholdNeuron extends Neuron {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Threshold value for this neuron
     */
    protected double thresh = 0;

    /**
     * Creates a neuron with threshold behaviour, and with the specified input
     * and transfer functions.
     *
     * @param inputFunction
     *            input function for this neuron
     * @param transferFunction
     *            transfer function for this neuron
     */
    public ThresholdNeuron(InputFunction inputFunction, TransferFunction transferFunction) {
        this.inputFunction = inputFunction;
        this.transferFunction = transferFunction;
        this.thresh = Math.random();
    }

    /**
     * Calculates neuron's output
     */
    @Override
    public void calculate() {
        this.totalInput = this.inputFunction.getOutput(this.inputConnections);
        this.output = this.transferFunction.getOutput(this.totalInput-this.thresh);
    }

    /**
     * Returns threshold value for this neuron
     * @return threshold value for this neuron
     */
    public double getThresh() {
        return thresh;
    }

    /**
     * Sets threshold value for this neuron
     * @param thresh threshold value for this neuron
     */
    public void setThresh(double thresh) {
        this.thresh = thresh;
    }

}

