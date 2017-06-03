package neuralnetwork2.events;


import neuralnetwork2.Layer;
import neuralnetwork2.NeuralNetwork;
import neuralnetwork2.Neuron;

/**
 * This class holds information about the source and type of some neural network event.
 * @author Zoran Sevarac <sevarac@gmail.com>
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