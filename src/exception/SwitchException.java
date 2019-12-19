package exception;

public class SwitchException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public SwitchException(String mes) {
        super(mes);
    }
}
