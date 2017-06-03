package neuralnetwork2.input;

import neuralnetwork2.Connection;

import java.io.Serializable;
import java.util.List;

/**
 *<pre>
 * Neuron's input function. It has two subcomponents:
 * 
 * weightsFunction - which performs operation with input and weight vector
 * summingFunction - which performs operation with the resulting vector from weightsFunction
 * 
 * InputFunction implements the following behaviour:
 * output = summingFunction(weightsFunction(inputs))
 * 
 * Different neuron input functions can be created by setting different weights and summing functions.
 *</pre>
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 * @see org.neuroph.core.Neuron
 */
abstract public class InputFunction implements Serializable {   // this should be functional interface!!!!!!!!
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 2L;
	

	/**
	 * Returns ouput value of this input function for the given neuron inputs
	 * 
	 * @param inputConnections
	 *            neuron's input connections
	 * @return input total net input
	 */
	abstract public double getOutput(List<Connection> inputConnections);

}
