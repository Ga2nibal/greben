package neuralnetwork2.transfer;

import java.io.Serializable;
import java.util.Properties;

import org.neuroph.util.TransferFunctionType;

/**
 * Sgn neuron transfer function.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class Sgn extends TransferFunction implements Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 *  y = 1, x > 0  
	 *  y = -1, x <= 0
	 */

	public double getOutput(double net) {
		if (net > 0d)
			return 1d;
		else
			return -1d;
	}

	/**
	 * Returns the properties of this function
	 * @return properties of this function
	 */	
	public Properties getProperties() {
		Properties properties = new java.util.Properties();
		properties.setProperty("transferFunction", TransferFunctionType.SGN.toString());
		return properties;
	}

}
