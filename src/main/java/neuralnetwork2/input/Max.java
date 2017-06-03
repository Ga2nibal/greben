package neuralnetwork2.input;

import neuralnetwork2.Connection;

import java.io.Serializable;
import java.util.List;

/**
 * Performs max function on input vector
 *
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class Max extends InputFunction implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization compatibility
     * with a previous version of the class.
     */
    private static final long serialVersionUID = 2L;

    @Override
    public double getOutput(List<Connection> inputConnections) {
        
        if (inputConnections.size() == 0) return 0;
        
        double max = inputConnections.get(0).getWeightedInput();

        for (Connection connection : inputConnections) {
            max = Math.max(max, connection.getWeightedInput());
        }

        return max;
    }
}
