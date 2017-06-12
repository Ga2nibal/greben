package neuralnetwork.base.events;


import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.Neuron;

/**
 * This class holds information about the source and type of some neural network event.
 */
public class NeuralNetworkEvent extends java.util.EventObject {
    
    Type eventType;
    
    public NeuralNetworkEvent(NeuralNetwork source, Type eventType) {
        super(source);
        this.eventType = eventType;
    }
    
    public NeuralNetworkEvent(Layer source, Type eventType) {
        super(source);
        this.eventType = eventType;
    }    

    public NeuralNetworkEvent(Neuron source, Type eventType) {
        super(source);
        this.eventType = eventType;
    }     
    
    public NeuralNetworkEvent.Type getEventType() {
        return eventType;
    }
    
    /**
     * Types of neural network events
     */
    public static enum Type {
        CALCULATED, 
        LAYER_ADDED,
        LAYER_REMOVED,
        NEURON_ADDED,
        NEURON_REMOVED,
        CONNECTION_ADDED,
        CONNECTION_REMOVED;
    }    

        
}