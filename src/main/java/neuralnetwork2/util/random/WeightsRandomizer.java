package neuralnetwork2.util.random;

import neuralnetwork2.Connection;
import neuralnetwork2.Layer;
import neuralnetwork2.NeuralNetwork;
import neuralnetwork2.Neuron;

import java.util.Random;

/**
 * Basic weights randomizer, iterates and randomizes all connection weights in network.
 */
public class WeightsRandomizer {

    /**
     * Random number genarator used to generate random values for weights
     */
    protected Random randomGen;

    /**
     * Create a new instance of WeightsRandomizer
     */
    public WeightsRandomizer() {
        this.randomGen = new Random();
    }

    /**
     * Create a new instance of WeightsRandomizer with specified random generator
     * If you use the same random generators, you'll get the same random sequences
     *
     * @param randomGen random geneartor to use for randomizing weights
     */
    public WeightsRandomizer(Random randomGen) {
        this.randomGen = randomGen;
    }

    public Random getRandomGen() {
        return randomGen;
    }

    /**
     * Iterates and randomizes all layers in specified network
     *
     * @param neuralNetwork neural network to randomize
     */
    public void randomize(NeuralNetwork<?> neuralNetwork) {
        for (Layer layer : neuralNetwork.getLayers()) {
            randomize(layer);
        }
    }

    /**
     * Iterate and randomizes all neurons in specified layer
     *
     * @param layer layer to randomize
     */
    protected void randomize(Layer layer) {
        for (Neuron neuron : layer.getNeurons()) {
            randomize(neuron);
        }
    }

    /**
     * Iterates and randomizes all connection weights in specified neuron
     *
     * @param neuron neuron to randomize
     */
    protected void randomize(Neuron neuron) {
        int numberOfInputConnections = neuron.getInputConnections().size();
        double coefficient = 1d / Math.sqrt(numberOfInputConnections);
        coefficient = coefficient == 0 ? 1 : coefficient;
        for (Connection connection : neuron.getInputConnections()) {
//            connection.getWeight().setValue(coefficient * nextRandomWeight());
            connection.getWeight().setValue(nextRandomWeight());

        }
    }

    /**
     * Returns next random value from random generator, that will be used to initialize weight
     * Override this method to implement custom random number generators
     *
     * @return next random value fro random generator
     */
    protected double nextRandomWeight() {
        return randomGen.nextDouble() - 0.5;
    }
}

