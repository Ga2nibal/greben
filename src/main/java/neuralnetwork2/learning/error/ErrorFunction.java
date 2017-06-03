package neuralnetwork2.learning.error;

/**
 * Interface for calculating total network error during the learning.
 * Custom error types  can be implemented.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public interface ErrorFunction {

    /**
     * Return total network error
     * 
     * @return total network error
     */
    public double getTotalError();

    /**
     * Calculates and adds pattern error for the given predicted and target output vector and adds it to total error.
     * @param predictedOutput actual network output
     * @param targetOutput target/desired output
     * @return returns pattern error vector
     */
    public double[] addPatternError(double[] predictedOutput, double[] targetOutput);
    
    /**
     * Sets total error and pattern count  to zero.
     */
    public void reset();    

}
