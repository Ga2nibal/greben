package neuralnetwork2.learning.stop;

import neuralnetwork2.learning.SupervisedLearning;

import java.io.Serializable;

/**
 * Stops learning rule if error change has been too small for specified number
 * of iterations
 */
public class SmallErrorChangeStop implements StopCondition, Serializable {

    private SupervisedLearning learningRule;

    public SmallErrorChangeStop(SupervisedLearning learningRule) {
        this.learningRule = learningRule;
    }

    @Override
    public boolean isReached() {
        if (learningRule.getMinErrorChangeIterationsCount() >= learningRule.getMinErrorChangeIterationsLimit()) {
            return true;
        }

        return false;
    }
}
