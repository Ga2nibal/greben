package neuralnetwork.implementation.neuron;

import neuralnetwork.base.Connection;
import neuralnetwork.base.Neuron;

/**
 * Neuron with constant high output (1), used as bias
 *
 * @see Neuron
 */
public class BiasNeuron extends Neuron {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance of BiasedNeuron.
     */
    public BiasNeuron() {
        super();
    }

    @Override
    public double getOutput() {
        return 1;
    }

    @Override
    public void addInputConnection(Connection connection) {

    }

    @Override
    public void addInputConnection(Neuron fromNeuron, double weightVal) {

    }

    @Override
    public void addInputConnection(Neuron fromNeuron){

    }



}

