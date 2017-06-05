package neuralnetwork2.util.data.sample;

import neuralnetwork2.data.DataSet;

import java.util.List;

/**
 * Interface for data set sampling  methods.
 */
public interface Sampling {

   List<DataSet> sample(DataSet dataSet);

}
