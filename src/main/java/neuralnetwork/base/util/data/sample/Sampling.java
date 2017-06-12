package neuralnetwork.base.util.data.sample;

import neuralnetwork.base.data.DataSet;

import java.util.List;

/**
 * Interface for data set sampling  methods.
 */
public interface Sampling {

   List<DataSet> sample(DataSet dataSet);

}
