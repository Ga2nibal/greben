package neuralnetwork2.util.plugins;

import neuralnetwork2.NeuralNetwork;

import java.io.Serializable;

/**
 * Base class for all neural network plugins.
 */
public class PluginBase implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name for this plugin
     */
    private String name;

    /**
     * Reference to parent neural network
     */
    private NeuralNetwork<?> parentNetwork;


    public PluginBase() {
    }


    /**
     * Creates an instance of plugin for neural network
     */
    public PluginBase(String name) {
        this.name=name;
    }

    /**
     * Returns the name of this plugin
     * @return name of this plugin
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the parent network for this plugin
     * @return parent network for this plugin
     */
    public NeuralNetwork<?> getParentNetwork() {
        return parentNetwork;
    }

    /**
     * Sets the parent network for this plugin
     * @param parentNetwork parent network for this plugin
     */
    public void setParentNetwork(NeuralNetwork parentNetwork) {
        this.parentNetwork = parentNetwork;
    }

}
