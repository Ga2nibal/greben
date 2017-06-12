package neuralnetwork.base.exceptions;

/**
 * Base exception type for Neuroph.
 */
public class NeuroNetworkException extends RuntimeException {
	/**
	 * The version ID.
	 */
	private static final long serialVersionUID = 0L;

	
	/**
	 * Default constructor.
	 */
	public NeuroNetworkException() {
		
	}
	
	/**
	 * Construct a message exception.
	 * 
	 * @param msg
	 *            The exception message.
	 */
	public NeuroNetworkException(final String msg) {
		super(msg);
	}

	/**
	 * Construct an exception that holds another exception.
	 * 
	 * @param t
	 *            The other exception.
	 */
	public NeuroNetworkException(final Throwable t) {
		super(t);
	}
	
	/**
	 * Construct an exception that holds another exception.
	 * 
	 * @param msg
	 *            A message.
	 * @param t
	 *            The other exception.
	 */
	public NeuroNetworkException(final String msg, final Throwable t) {
		super(msg, t);
	}
}
