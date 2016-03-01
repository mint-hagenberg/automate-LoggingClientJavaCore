package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

/**
 * A {@code ManagerException} is thrown to indicate that the attempt to start a service has failed.
 */
public class ManagerException extends Exception {
	private static final long serialVersionUID = 6195244931574767661L;

	/**
	 * Creates a new {@code ManagerException} with no detail message.
	 */
	public ManagerException() {
		super();
	}

	/**
	 * Creates a new {@code ManagerException} with specified detail message.
	 *
	 * @param message the detail message
	 */
	public ManagerException(String message) {
		super(message);
	}

	/**
	 * Creates a new {@code ManagerException} with specified cause.
	 *
	 * @param cause the cause
	 */
	public ManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new {@code ManagerException} with specified detail message and
	 * cause.
	 *
	 * @param message the detail message
	 * @param cause   the cause
	 */
	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
