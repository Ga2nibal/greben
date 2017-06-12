package neuralnetwork.base.input;

import neuralnetwork.base.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Performs multiplication of all input vector elements.
 */
public class Product extends InputFunction implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization compatibility
     * with a previous version of the class.
     */
    private static final long serialVersionUID = 2L;

    @Override
    public double getOutput(List<Connection> inputConnections) {
        if (inputConnections.size() == 0) {
            return 0d;
        }

        double output = 1d;

        for (Connection connection : inputConnections) {
            output *= connection.getInput();
        }

        return output;
    }
}
