package neuralnetwork2.input;

import neuralnetwork2.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Performs logic OR operation on input vector.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class Or extends InputFunction implements Serializable {
	
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

		boolean output = false;
		
		for (Connection connection : inputConnections) {
			output = output || (connection.getInput() >= 0.5d);
		}

		return output ? 1d : 0d;
	}

}
