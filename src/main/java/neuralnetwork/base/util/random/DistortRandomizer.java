package neuralnetwork.base.util.random;

import neuralnetwork.base.Connection;
import neuralnetwork.base.Neuron;

/**
 * This class provides distort randomization technique, which distorts existing
 * weight values using specified distortion factor.
 * Weights are distorted using following formula:
 * newWeightValue = currentWeightValue + (distortionFactor - (random * distortionFactor * 2))
 */
public class DistortRandomizer extends WeightsRandomizer {

    /**
     * Distrotion factor which determines the amount to distort existing weight values
     */
    double distortionFactor;

    /**
     * Create a new instance of DistortRandomizer with specified distortion factor
     * @param distortionFactor amount to distort existing weights
     */
    public DistortRandomizer(double distortionFactor) {
        this.distortionFactor = distortionFactor;
    }

    /**
     * Iterate all layers, neurons and connection weight and apply distort randomization
     * @param neuron
     */
    @Override
    protected void randomize(Neuron neuron) {
        for (Connection connection : neuron.getInputConnections()) {
            double weight = connection.getWeight().getValue();
            connection.getWeight().setValue(distort(weight));
        }
    }


    /**
     * Returns distorted weight value
     * @param weight current weight value
     * @return distorted weight value
     */
    private double distort(double weight) {
        return  weight + (this.distortionFactor - (randomGen.nextDouble() * this.distortionFactor * 2));
    }

}

