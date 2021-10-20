package eu.smashmc.api;

/**
 * Exception that indicates an invalid/unsupported implementation of a
 * {@link SmashComponent} was detected at runtime.
 * 
 * @author LiquidDev
 *
 */
public class IllegalImplementationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalImplementationException(String message) {
		super(message);
	}

	public IllegalImplementationException(String message, Exception cause) {
		super(message, cause);
	}
}
