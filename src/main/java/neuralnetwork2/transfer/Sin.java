package neuralnetwork2.transfer;

/**
 * <pre>
 * Sin neuron transfer function.
 * 
 * output = sin(input)
 * </pre>
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class Sin extends TransferFunction {

    @Override
    public double getOutput(double net) {
        return Math.sin(net);
    }
    
    @Override
    public double getDerivative(double net) {
	return Math.cos(net);
    }    
         
}
