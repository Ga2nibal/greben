package neuralnetwork.base.util.random;

/**
 * This class provides Gaussian randomization technique using Box Muller method.
 * Based on GaussianRandomizer from Encog
 */
public class GaussianRandomizer extends WeightsRandomizer {

    double mean;
    double standardDeviation;

    /**
     * The y2 value.
     */
    private double y2;

    /**
     * Should we use the last value.
     */
    private boolean useLast = false;

    public GaussianRandomizer(double mean, double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }

    /**
     * Compute a Gaussian random number.
     *
     * @param mean
     *            The mean.
     * @param std
     *            The standard deviation.
     * @return The random number.
     */
    private double boxMuller(double mean, double std) {
        double x1, x2, w, y1;

        // use value from previous call
        if (this.useLast) {
            y1 = this.y2;
            this.useLast = false;
        } else {
            do {
                x1 = 2.0 * randomGen.nextDouble() - 1.0;
                x2 = 2.0 * randomGen.nextDouble() - 1.0;
                w = x1 * x1 + x2 * x2;
            } while (w >= 1.0);

            w = Math.sqrt((-2.0 * Math.log(w)) / w);
            y1 = x1 * w;
            this.y2 = x2 * w;
            this.useLast = true;
        }

        return (mean + y1 * std);
    }

    @Override
    protected double nextRandomWeight() {
        return boxMuller(mean, standardDeviation);
    }
}

