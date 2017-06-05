package neuralnetwork2.util;

import neuralnetwork2.Neuron;
import neuralnetwork2.exceptions.NeuroNetworkException;
import neuralnetwork2.input.InputFunction;
import neuralnetwork2.nnet.neuron.InputOutputNeuron;
import neuralnetwork2.nnet.neuron.ThresholdNeuron;
import neuralnetwork2.transfer.TransferFunction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Provides methods to create customized instances of Neuron.
 */
public class NeuronFactory {

    /**
     * Private constructor prevents creating an instances of this class.
     */
    private NeuronFactory() { }

    /**
     * Creates and returns neuron instance according to the given specification in neuronProperties.
     *
     * @param neuronProperties
     *            specification of neuron properties
     * @return returns instance of neuron with specified properties
     */
    public static Neuron createNeuron(NeuronProperties neuronProperties) {

        InputFunction inputFunction = null;
        Class inputFunctionClass = neuronProperties.getInputFunction();

        if ( inputFunctionClass != null) {
            inputFunction = createInputFunction(inputFunctionClass);
        }

        TransferFunction transferFunction = createTransferFunction(neuronProperties.getTransferFunctionProperties());

        Neuron neuron = null;
        Class neuronClass = neuronProperties.getNeuronType();

        // use two param constructor to create neuron
        try {
            Class[] paramTypes = {InputFunction.class, TransferFunction.class};
            Constructor con = neuronClass.getConstructor(paramTypes);

            Object[] paramList = new Object[2];
            paramList[0] = inputFunction;
            paramList[1] = transferFunction;

            neuron = (Neuron) con.newInstance(paramList);

        }  catch (NoSuchMethodException e) {
            //    throw new NeurophException("NoSuchMethod while creating Neuron!", e);
        } catch (InstantiationException e) {
            throw new NeuroNetworkException("InstantiationException while creating Neuron!", e);
        } catch (IllegalAccessException e) {
            throw new NeuroNetworkException("IllegalAccessException while creating Neuron!", e);
        } catch (InvocationTargetException e) {
            throw new NeuroNetworkException("InvocationTargetException while creating Neuron!", e);
        }

        if (neuron == null) {
            // use constructor without params to create neuron
            try {
                neuron = (Neuron) neuronClass.newInstance();
            } catch(IllegalAccessException e) {
                System.err.println("InstantiationException while creating Neuron!");
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.err.println("InstantiationException while creating Neuron!");
                e.printStackTrace();
            }
        }

        if  (neuronProperties.hasProperty("thresh")) {
            ((ThresholdNeuron)neuron).setThresh((Double)neuronProperties.getProperty("thresh"));
        } else if  (neuronProperties.hasProperty("bias")) {
            ((InputOutputNeuron)neuron).setBias((Double)neuronProperties.getProperty("bias"));
        }

        return neuron;

    }


    private static InputFunction createInputFunction(Class inputFunctionClass) {
        InputFunction inputFunction = null;

        try {
            inputFunction = (InputFunction) inputFunctionClass.newInstance();
        } catch (InstantiationException e) {
            throw new NeuroNetworkException("InstantiationException while creating InputFunction!", e);
        } catch (IllegalAccessException e) {
            throw new NeuroNetworkException("IllegalAccessException while creating InputFunction!", e);
        }

        return inputFunction;
    }


    /**
     * Creates and returns instance of transfer function
     *
     * @param tfProperties
     *            transfer function properties
     * @return returns transfer function
     */
    private static TransferFunction createTransferFunction(Properties tfProperties) {
        TransferFunction transferFunction = null;

        Class  tfClass = (Class)tfProperties.getProperty("transferFunction");

        try {
            Class[] paramTypes = null;

            Constructor[] cons = tfClass.getConstructors();
            for (int i=0; i<cons.length; i++) {
                paramTypes = cons[i].getParameterTypes();

                // use constructor with one parameter of Properties type
                if ((paramTypes.length == 1) && (paramTypes[0] == Properties.class)) {
                    Class[] argTypes = new Class[1];
                    argTypes[0] = Properties.class;
                    Constructor ct = tfClass.getConstructor(argTypes);

                    Object[] argList = new Object[1];
                    argList[0] = tfProperties;
                    transferFunction = (TransferFunction) ct.newInstance(argList);
                    break;
                } else if(paramTypes.length == 0) { // use constructor without params
                    transferFunction = (TransferFunction) tfClass.newInstance();
                    break;
                }
            }

            return transferFunction;

        } catch (NoSuchMethodException e) {
            System.err.println("getConstructor() couldn't find the constructor while creating TransferFunction!");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("InstantiationException while creating TransferFunction!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("No permission to invoke method while creating TransferFunction!");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.err.println("Method threw an: " + e.getTargetException() + " while creating TransferFunction!");
            e.printStackTrace();
        }

        return transferFunction;
    }

}
