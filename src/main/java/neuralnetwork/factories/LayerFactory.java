package neuralnetwork.factories;

import neuralnetwork.base.Layer;
import neuralnetwork.base.Neuron;
import neuralnetwork.base.transfer.TransferFunction;
import neuralnetwork.base.util.NeuronProperties;
import neuralnetwork.base.util.TransferFunctionType;

import java.util.List;

/**
 * Provides methods to create instance of a Layer with various setting (number of neurons and neuron's properties, etc.
 */
public class LayerFactory {

    /**
     * Private constructor prevents creating an instances of this class.
     */
    private LayerFactory() { }


    /**
     * Creates and returns instance of Layer with specified number of neurons with specified properties
     * @param neuronsCount
     * @param neuronProperties
     * @return
     */
    public static Layer createLayer(int neuronsCount, NeuronProperties neuronProperties) {
        Layer layer = new Layer(neuronsCount, neuronProperties);
        return layer;
    }

    public static Layer createLayer(int neuronsCount, TransferFunctionType transferFunctionType) {
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transferFunction", transferFunctionType);
        Layer layer = new Layer(neuronsCount, neuronProperties);
        return layer;
    }

    public static Layer createLayer(int neuronsCount, Class <? extends TransferFunction> transferFunctionClass) {
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transferFunction", transferFunctionClass);
        Layer layer = new Layer(neuronsCount, neuronProperties);
        return layer;
    }

    public static Layer createLayer(List<NeuronProperties> neuronPropertiesVector) {
        Layer layer = new Layer();

        for(NeuronProperties neuronProperties : neuronPropertiesVector) {
            Neuron neuron = NeuronFactory.createNeuron(neuronProperties);
            layer.addNeuron(neuron);
        }

        return layer;
    }

}
