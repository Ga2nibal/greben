package neuralnetwork.implementation;

import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.Neuron;
import neuralnetwork.base.input.Difference;
import neuralnetwork.base.transfer.Linear;
import neuralnetwork.base.util.NeuralNetworkType;
import neuralnetwork.base.util.NeuronProperties;
import neuralnetwork.factories.ConnectionFactory;
import neuralnetwork.factories.LayerFactory;
import neuralnetwork.factories.NeuralNetworkFactory;
import neuralnetwork.implementation.leaning.KohonenLearning;

/**
 * Kohonen neural network.
 */
public class Kohonen extends NeuralNetwork {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates new Kohonen network with specified number of neurons in input and
     * map layer
     *
     * @param inputNeuronsCount
     *            number of neurons in input layer
     * @param outputNeuronsCount
     *            number of neurons in output layer
     */
    public Kohonen(int inputNeuronsCount, int outputNeuronsCount) {
        this.createNetwork(inputNeuronsCount, outputNeuronsCount);
    }

    /**
     * Creates Kohonen network architecture with specified number of neurons in
     * input and map layer
     *
     * @param inputNeuronsCount
     *            number of neurons in input layer
     * @param outputNeuronsCount
     *            number of neurons in output layer
     */
    private void createNetwork(int inputNeuronsCount, int outputNeuronsCount) {

        // specify input neuron properties (use default: weighted sum input with
        // linear transfer)
        NeuronProperties inputNeuronProperties = new NeuronProperties();

        // specify map neuron properties
        NeuronProperties outputNeuronProperties = new NeuronProperties(
                Neuron.class,        // neuron type
                Difference.class,   // input function
                Linear.class       // transfer function
        );
        // set network type
        this.setNetworkType(NeuralNetworkType.KOHONEN);

        // createLayer input layer
        Layer inLayer = LayerFactory.createLayer(inputNeuronsCount,
                inputNeuronProperties);
        this.addLayer(inLayer);

        // createLayer map layer
        Layer mapLayer = LayerFactory.createLayer(outputNeuronsCount,
                outputNeuronProperties);
        this.addLayer(mapLayer);

        // createLayer full connectivity between input and output layer
        ConnectionFactory.fullConnect(inLayer, mapLayer);

        // set network input and output cells
        NeuralNetworkFactory.setDefaultIO(this);

        this.setLearningRule(new KohonenLearning());
    }

}

