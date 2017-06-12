package neuralnetwork.base.input;

import neuralnetwork.base.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Performs logic AND operation on input vector.
 */
public class And extends InputFunction implements Serializable {

	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param inputVector Input values >= 0.5d are considered true, otherwise false.
	 */
	public double getOutput(List<Connection> inputConnections) {

                if (inputConnections.size() == 0) return 0d;

		boolean output = true;
		
		for (Connection connection : inputConnections) {
			output = output && (connection.getInput() >= 0.5d);
		}

		return output ? 1d : 0d;
	}

}
