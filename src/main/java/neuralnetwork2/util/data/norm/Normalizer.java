package neuralnetwork2.util.data.norm;

import neuralnetwork2.data.DataSet;

/**
 * Interface for data set normalization methods.
 */
public interface Normalizer {

    /**
     * Normalize specified data set
     * @param dataSet data set to normalize
     */
    void normalize(DataSet dataSet);

}