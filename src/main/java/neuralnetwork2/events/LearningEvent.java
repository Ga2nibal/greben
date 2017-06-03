package neuralnetwork2.events;

import neuralnetwork2.learning.LearningRule;

/**
 * This class holds information about the source of some learning event.
  * @author Zoran Sevarac
 */

public class LearningEvent extends java.util.EventObject {
    
    LearningEvent.Type eventType;
    
    public LearningEvent(LearningRule source, LearningEvent.Type eventType) {
        super(source);
        this.eventType = eventType;
    }

    public LearningEvent.Type getEventType() {
        return eventType;
    }
    
    // Add types of learning events you want to listen to to this enum
    public static enum Type {
        EPOCH_ENDED, LEARNING_STOPPED;
    }
    
                
}