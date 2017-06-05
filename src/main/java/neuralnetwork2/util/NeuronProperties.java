package neuralnetwork2.util;

import neuralnetwork2.Neuron;
import neuralnetwork2.input.InputFunction;
import neuralnetwork2.input.WeightedSum;
import neuralnetwork2.transfer.Linear;
import neuralnetwork2.transfer.TransferFunction;

import java.util.Iterator;

/**
 * Represents properties of a neuron.
 */
public class NeuronProperties extends Properties {

    private static final long serialVersionUID = 2L;

    // public static final DEFAULT = new NeuronProperties();


    public NeuronProperties() {
        initKeys();
        this.setProperty("inputFunction", WeightedSum.class);
        this.setProperty("transferFunction", Linear.class);
        this.setProperty("neuronType", Neuron.class);
    }

    public NeuronProperties(Class<? extends Neuron> neuronClass) {
        initKeys();
        this.setProperty("inputFunction", WeightedSum.class);
        this.setProperty("transferFunction", Linear.class);
        this.setProperty("neuronType", neuronClass);
    }

    public NeuronProperties(Class<? extends Neuron> neuronClass, Class<? extends TransferFunction> transferFunctionClass) {
        initKeys();
        this.setProperty("inputFunction", WeightedSum.class);
        this.setProperty("transferFunction", transferFunctionClass);
        this.setProperty("neuronType", neuronClass);
    }

    public NeuronProperties(Class<? extends Neuron> neuronClass,
                            Class<? extends InputFunction> inputFunctionClass,
                            Class<? extends TransferFunction> transferFunctionClass) {
        initKeys();
        this.setProperty("inputFunction", inputFunctionClass);
        this.setProperty("transferFunction", transferFunctionClass);
        this.setProperty("neuronType", neuronClass);
    }

    public NeuronProperties(Class<? extends Neuron> neuronClass, TransferFunctionType transferFunctionType) {
        initKeys();
        this.setProperty("inputFunction", WeightedSum.class);
        this.setProperty("transferFunction", transferFunctionType.getTypeClass());
        this.setProperty("neuronType", neuronClass);
    }

    public NeuronProperties(TransferFunctionType transferFunctionType, boolean useBias) {
        initKeys();
//		this.setProperty("weightsFunction", WeightedInput.class);
//		this.setProperty("summingFunction", Sum.class);
        this.setProperty("inputFunction", WeightedSum.class);
        this.setProperty("transferFunction", transferFunctionType.getTypeClass());
        this.setProperty("useBias", useBias);
        this.setProperty("neuronType", Neuron.class);
    }

    // uraditi override za setProperty tako da za enum type uzima odgovarajuce klase
    private void initKeys() {
        createKeys("inputFunction", "transferFunction", "neuronType", "useBias"); // use bias prebaciti u NeuralNetworkProperties
    }

    public Class getInputFunction() {
        Object val = this.get("inputFunction");
        if (!val.equals("")) {
            return (Class) val;
        }
        return null;
    }

    public Class getTransferFunction() {
        return (Class) this.get("transferFunction");
    }

    public Class getNeuronType() {
        return (Class) this.get("neuronType");
    }

    public Properties getTransferFunctionProperties() {
        Properties tfProperties = new Properties();
        //Enumeration<?> en = this.keys();
        Iterator iterator =  this.keySet().iterator();
        while (iterator.hasNext()) {
            String name = iterator.next().toString();
            if (name.contains("transferFunction")) {
                tfProperties.setProperty(name, this.get(name));
            }
        }
        return tfProperties;
    }

    @Override
    public final void setProperty(String key, Object value) {
//                if (!this.containsKey(key))
//                    throw new RuntimeException("Unknown property key: "+key);

        if (value instanceof TransferFunctionType) {
            value = ((TransferFunctionType) value).getTypeClass();
        }
        //      if (value instanceof InputFunctionType) value = ((InputFunctionType)value).getTypeClass();

        this.put(key, value);
    }
}
