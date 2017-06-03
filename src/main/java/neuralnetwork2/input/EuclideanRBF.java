package neuralnetwork2.input;

import neuralnetwork2.Connection;
import neuralnetwork2.Neuron;
import neuralnetwork2.Weight;

import java.util.List;

/**
 *
 * @author zoran
 */
public class EuclideanRBF extends InputFunction {

    @Override
    public double getOutput(List<Connection> inputConnections) {
	//	double output = 0d;

               double sqrSum = 0d;
		for(Connection connection : inputConnections) {
			Neuron neuron = connection.getFromNeuron();
			Weight weight = connection.getWeight();
			double diff = neuron.getOutput() - weight.getValue();
                        sqrSum += diff * diff;
		}
               
		return  0.5 *Math.sqrt(sqrSum) / (double)inputConnections.size(); // ovo trebaprebaciti u novu funkciju transfera sa odgovarajucim izvodom
    }
    
}
