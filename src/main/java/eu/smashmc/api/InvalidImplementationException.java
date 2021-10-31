package eu.smashmc.api;

/**
 * Exception that indicates an invalid/unsupported implementation of a
 * {@link SmashComponent} was detected at runtime.
 * 
 * @author LiquidDev
 *
 */
public class InvalidImplementationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidImplementationException(String message) {
		super(message);
	}

	public InvalidImplementationException(String message, Exception cause) {
		super(message, cause);
	}
}
