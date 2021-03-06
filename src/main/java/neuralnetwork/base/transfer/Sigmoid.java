package neuralnetwork.base.transfer;

import java.io.Serializable;
import neuralnetwork.base.util.Properties;

/**
 * <pre>
 * Sigmoid neuron transfer function.
 * 
 * output = 1/(1+ e^(-slope*input))
 * </pre>
 */
public class Sigmoid extends TransferFunction implements Serializable {
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */		
	private static final long serialVersionUID = 2L;
	
	/**
	 * The slope parametetar of the sigmoid function
	 */
	private double slope = 1d;

	/**
	 * Creates an instance of Sigmoid neuron transfer function with default
	 * slope=1.
	 */	
	public Sigmoid() {
	}

	/**
	 * Creates an instance of Sigmoid neuron transfer function with specified
	 * value for slope parametar.
	 * @param slope the slope parametar for the sigmoid function
	 */
	public Sigmoid(double slope) {
		this.slope = slope;
	}

	/**
	 * Creates an instance of Sigmoid neuron transfer function with the
	 * specified properties.
	 * @param properties properties of the sigmoid function
	 */	
	public Sigmoid(Properties properties) {
		try {
			this.slope = (Double)properties.getProperty("transferFunction.slope");
		} catch (NullPointerException e) {
			// if properties are not set just leave default values
		} catch (NumberFormatException e) {
			System.err.println("Invalid transfer function properties! Using default values.");
		}
	}
	
	/**
	 * Returns the slope parametar of this function
	 * @return  slope parametar of this function 
	 */
	public double getSlope() {
		return this.slope;
	}

	/**
	 * Sets the slope parametar for this function
	 * @param slope value for the slope parametar
	 */
	public void setSlope(double slope) {
		this.slope = slope;
	}

	@Override
	public double getOutput(double netInput) {
                // conditional logic helps to avoid NaN
                if (netInput > 100) {
                    return 1.0;
                }else if (netInput < -100) {
                    return 0.0;
                }

		double den = 1 + Math.exp(-this.slope * netInput);
                this.output = (1d / den);
                
		return this.output;
	}

	@Override
	public double getDerivative(double net) { // remove net parameter? maybe we dont need it since we use cached output value
                // +0.1 is fix for flat spot see http://www.heatonresearch.com/wiki/Flat_Spot
		double derivative = this.slope * this.output * (1d - this.output) + 0.1;
		return derivative;
	}

}
