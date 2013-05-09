package exception;


public class ConfigException extends RYException {
	private static final long serialVersionUID = -1L;

	/**
	 * @param errorCode
	 *            ,
	 * @param errorMessage
	 *            String errorMessage
	 */
	public ConfigException(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	/**
	 * @param errorCode
	 *            ,
	 * @param errorMessage
	 *            String
	 * @param t
	 *            exception
	 */
	public ConfigException(ErrorCode errorCode, String errorMessage, Throwable t) {
		super(errorCode, errorMessage, t);
	}
}
