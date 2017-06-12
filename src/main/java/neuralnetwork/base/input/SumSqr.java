package neuralnetwork.base.input;

import neuralnetwork.base.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Calculates squared sum of all input vector elements.
 */
public class SumSqr extends InputFunction implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization compatibility
     * with a previous version of the class.
     */
    private static final long serialVersionUID = 2L;

    @Override
    public double getOutput(List<Connection> inputConnections) {
        double output = 0d;

        for (Connection connection : inputConnections) {
            double input = connection.getInput();
            output += input * input;
        }

        return output;
    }
}
