package neuralnetwork2.learning.error;

import java.io.Serializable;

/**
 * Commonly used mean squared error
 *
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public final class MeanSquaredError implements ErrorFunction, Serializable {

    private static final long serialVersionUID = 1L;
    
    private transient double totalError;
    
    /**
     * Number of patterns - n 
     */
    private transient double patternCount;

    public MeanSquaredError() {
        reset();
    }

    
    @Override
    public double[] addPatternError(double[] predictedOutput, double[] targetOutput) {
        double[] patternError = new double[targetOutput.length];

        for (int i = 0; i < predictedOutput.length; i++) {
            patternError[i] =  predictedOutput[i] - targetOutput[i];
            totalError += patternError[i] * patternError[i];
        }
        
        patternCount++;
        return patternError;
    }
    
    @Override
    public void reset() {
        totalError = 0d;
        patternCount = 0;
    }

    @Override
    public double getTotalError() {
        return totalError / (2 *  patternCount );
    }

}