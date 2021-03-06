package neuralnetwork.base.util.data.sample;

import neuralnetwork.base.data.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides subsampling of a data set, and creates a specified number of subsets of a
 * specified number of samples form given data set.
 */
public class SubSampling implements Sampling {


    /**
     * Number of sub sets
     */
    private int subSetCount;

    /**
     * Sizes of each subset
     */
    private int[] subSetSizes;

    /**
     * True if samples are allowed to repeat in different subsets
     */
    private boolean allowRepetition = false;


    /**
     * Sampling will produce a specified number of subsets of equal sizes
     * Handy for K Fold subsampling
     *
     * @param subSetCount number of subsets to produce
     */
    public SubSampling(int subSetCount) { // without repetition
        this.subSetCount = subSetCount;
        this.subSetSizes = null;
    }


    /**
     * Sampling will produce subsets of specified sizes (in percents)
     *
     * @param subSetSizes size of subsets in percents
     */
    public SubSampling(int ... subSetSizes) { // without repetition
        // sum of these must be 100%???
        this.subSetSizes = subSetSizes;
        this.subSetCount = subSetSizes.length;
    }


    @Override
    public List<DataSet> sample(DataSet dataSet) {
        if (subSetSizes == null) { // if number of subSetSizes is null calculate it based on subSetSount
            int  singleSubSetSize = dataSet.size() / subSetCount;
            subSetSizes = new int[subSetCount];
            for(int i=0; i< subSetCount; i++)
                subSetSizes[i] = singleSubSetSize;
        }

        List<DataSet> subSets = new ArrayList<>();

        // shuffle dataset in order to randomize rows that will be used to fill subsets
        dataSet.shuffle();

        int inputSize = dataSet.getInputSize();
        int outputSize = dataSet.getOutputSize();

        int idxCounter = 0;
        for(int s=0; s < subSetSizes.length; s++) {
            // create new sample subset
            DataSet newSubSet = new DataSet(inputSize, outputSize);
            // fill subset with rows

            if (!allowRepetition) {
                int foldSize =  (int)(((double)subSetSizes[s] / 100) * dataSet.size());
                for (int i = 0; i < foldSize; i++) {
                    if(idxCounter >= dataSet.size()) break;
                    newSubSet.addRow(dataSet.getRowAt(idxCounter));
                    idxCounter++;
                }
            } else {
                int randomIdx;
                Random rand = new Random();
                for (int i = 0; i < subSetSizes[s] /100 * dataSet.size(); i++) {
                    randomIdx = rand.nextInt(dataSet.size());
                    newSubSet.addRow(dataSet.getRowAt(randomIdx));
                    idxCounter++;
                }
            }
            // add subset to th elist of subsets to return
            subSets.add(newSubSet);
        }

        return subSets;
    }

    /**
     * Get flag which indicates if sample repetition is allowed in subsets
     * @return
     */
    public boolean getAllowRepetition() {
        return allowRepetition;
    }

    /**
     * Set flag to allow repetition of samples in subsets
     * @param allowRepetition
     */
    public void setAllowRepetition(boolean allowRepetition) {
        this.allowRepetition = allowRepetition;
    }



}
