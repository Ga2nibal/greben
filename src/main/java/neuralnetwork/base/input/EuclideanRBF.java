package neuralnetwork.base.input;

import neuralnetwork.base.Connection;
import neuralnetwork.base.Neuron;
import neuralnetwork.base.Weight;

import java.util.List;

/**
 *
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
