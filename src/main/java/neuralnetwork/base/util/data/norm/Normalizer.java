package neuralnetwork.base.util.data.norm;

import neuralnetwork.base.data.DataSet;

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