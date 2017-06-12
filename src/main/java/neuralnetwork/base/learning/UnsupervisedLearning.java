package neuralnetwork.base.learning;

import neuralnetwork.base.data.DataSet;
import neuralnetwork.base.data.DataSetRow;

import java.io.Serializable;
import java.util.Iterator;


/**
 * Base class for all unsupervised learning algorithms.
 */
abstract public class UnsupervisedLearning extends IterativeLearning implements
		Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization 
	 * compatibility with a previous version of the class
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new unsupervised learning rule
	 */
	public UnsupervisedLearning() {
		super();
	}


	/**
	 * This method does one learning epoch for the unsupervised learning rules.
	 * It iterates through the training set and trains network weights for each
	 * element
	 * 
	 * @param trainingSet
	 *            training set for training network
	 */
         @Override
	public void doLearningEpoch(DataSet trainingSet) {
		Iterator<DataSetRow> iterator = trainingSet.iterator();
		while (iterator.hasNext() && !isStopped()) {
			DataSetRow trainingSetRow = iterator.next();
			learnPattern(trainingSetRow);
		}
	}	
	
	/**
	 * Trains network with the pattern from the specified training element
	 * 
	 * @param DataSetItem
	 *            unsupervised training element which contains network input
	 */
	protected void learnPattern(DataSetRow trainingElement) {
		double[] input = trainingElement.getInput();
		this.neuralNetwork.setInput(input);
		this.neuralNetwork.calculate();
		this.updateNetworkWeights();
	}



	/**
	 * This method implements the weight adjustment
	 */
	abstract protected void updateNetworkWeights();

}