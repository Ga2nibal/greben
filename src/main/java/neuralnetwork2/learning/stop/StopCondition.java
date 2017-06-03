package neuralnetwork2.learning.stop;

/**
 * Interface for learning rule stop condition.
 * Classes implementing this interface should just implement one method and
 * return true if learning rule should stop
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public interface StopCondition {
    /**
     * Returns true if learning rule should stop, false otherwise
     * @return true if learning rule should stop, false otherwise
     */
    public boolean isReached();         
}
