package neuralnetwork.base.learning.stop;

import neuralnetwork.base.learning.IterativeLearning;

import java.io.Serializable;

/**
 * Stops learning rule if specified number of iterations has been reached
 */
public class MaxIterationsStop implements StopCondition, Serializable {

    private IterativeLearning learningRule;
    
    public MaxIterationsStop(IterativeLearning learningRule) {
        this.learningRule = learningRule;
    }
        
    @Override
    public boolean isReached() {
        
        
        if (learningRule.getCurrentIteration() >= learningRule.getMaxIterations()) {
            return true;
        }
        
        return false;
    }
    
    
}