package neuralnetwork.base.learning.stop;

import neuralnetwork.base.learning.SupervisedLearning;

import java.io.Serializable;

/**
 * Stops learning rule if total network error is below some specified value
 */
public class MaxErrorStop implements StopCondition, Serializable {

    private final SupervisedLearning learningRule;
    
    public MaxErrorStop(SupervisedLearning learningRule) {
        this.learningRule = learningRule;
    }
    
    @Override
    public boolean isReached() {
        if (learningRule.getTotalNetworkError() < learningRule.getMaxError()) {
            return true;
        }
        
        return false;
    }
           
}
