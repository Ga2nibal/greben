package neuralnetwork2.input;

import neuralnetwork2.Connection;
import neuralnetwork2.Neuron;
import neuralnetwork2.Weight;

import java.io.Serializable;
import java.util.List;

/**
 * Performs the vector difference operation on input and
 * weight vector.
 */
public class Difference extends InputFunction implements Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */		
	private static final long serialVersionUID =21L;
	

    @Override
	public double getOutput(List<Connection> inputConnections) {
		double output = 0d;

                double sum = 0d;
		for(Connection connection : inputConnections) {
			Neuron neuron = connection.getFromNeuron();
			Weight weight = connection.getWeight();
			double diff = neuron.getOutput() - weight.getValue();
                        sum += diff * diff;
		}

                output = Math.sqrt(sum);
                
		return output;
	}

	public double[] getOutput(double[] inputs, double[] weights) {
		double[] output = new double[inputs.length];

		for(int i = 0; i < inputs.length; i++) {
			output[i] = inputs[i] - weights[i];
		}

		return output;
	}

}
