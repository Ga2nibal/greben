package neuralnetwork2.util;

import java.util.HashMap;

/**
 * Represents a general set of properties for neuroph objects
 */
public class Properties extends HashMap {
    private static final long serialVersionUID = 1L;


    protected void createKeys(String ... keys) {
        for(int i=0; i<keys.length; i++)
            this.put(keys[i], "");
    }

    public void setProperty(String key, Object value) {
//                if (!this.containsKey(key))
//                    throw new RuntimeException("Unknown property key: "+key);

        this.put(key, value);
    }

    public Object getProperty(String key) {
//                if (!this.containsKey(key))
//                        throw new RuntimeException("Unknown property key: "+key);

        return this.get(key);
    }

    public boolean hasProperty(String key) {
        return this.containsKey(key);
    }

}