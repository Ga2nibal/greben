package neuralnetwork.base.events;

/**
 * This interface is implemented by classes who are listening to neural network events events (to be defined)
 * NeuralNetworkEvent class holds the information about event.
 */
@FunctionalInterface
public interface NeuralNetworkEventListener extends  java.util.EventListener {
    
      public void handleNeuralNetworkEvent(NeuralNetworkEvent event);
}
