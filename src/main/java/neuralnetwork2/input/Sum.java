package neuralnetwork2.input;

import neuralnetwork2.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Performs summing of all input vector elements.
 */
public class Sum extends InputFunction implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization compatibility
     * with a previous version of the class.
     */
    private static final long serialVersionUID = 2L;

    @Override
    public double getOutput(List<Connection> inputConnections) {
        double output = 0d;

        for (Connection connection : inputConnections) {
            output += connection.getInput();
        }

        return output;
    }
}