package neuralnetwork2.transfer;

import java.io.Serializable;

/**
 * Abstract base class for all neuron tranfer functions.
 */
abstract public class TransferFunction implements Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */		
	private static final long serialVersionUID = 1L;
        
        /**
         * Output result
         */
        protected transient double output; // cached output value to avoid double calculation for derivative

	/**
	 * Returns the ouput of this function.
	 * 
	 * @param totalInput
	 *            total input 
	 */
	abstract public double getOutput(double totalInput);

	/**
	 * Returns the first derivative of this function.
	 * Note: should this method should be abstract?
	 * @param totalInput
	 *            total  input
	 */
	public double getDerivative(double totalInput) {
		return 1d;
	}

}
