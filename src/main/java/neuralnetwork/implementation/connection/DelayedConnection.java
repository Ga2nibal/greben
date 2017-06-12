package neuralnetwork.implementation.connection;

import neuralnetwork.base.Connection;
import neuralnetwork.base.Neuron;
import neuralnetwork.implementation.neuron.DelayedNeuron;

/**
 * Represents the connection between neurons which can delay signal.
 *
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class DelayedConnection extends Connection {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Delay factor for this conection
     */
    private int delay = 0;

    /**
     * Creates an instance of delayed connection to cpecified neuron and
     * with specified weight
     * @param fromNeuron neuron to connect (source neuron)
     * @param toNeuron neuron to connect to (destination neuron)
     * @param weightVal weight value for the connection
     * @param delay delay for the connection
     */
    public DelayedConnection(Neuron fromNeuron, Neuron toNeuron, double weightVal, int delay) {
        super(fromNeuron, toNeuron, weightVal);
        this.delay = delay;
    }

    /**
     * Returns delay value for this connection
     * @return delay value for this connection
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Sets delay value for this connection
     * @param delay value for this connection
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Gets delayed input through this connection
     * @return delayed output from connected neuron
     */
    @Override
    public double getInput() {
        if (this.fromNeuron instanceof DelayedNeuron)
            return ((DelayedNeuron) this.fromNeuron).getOutput(delay);
        else
            return this.fromNeuron.getOutput();
    }

}

