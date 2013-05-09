package exception;


public abstract class RYException extends Exception {
	private static final long serialVersionUID = -1L;
	private ErrorCode errorCode;

	/**
	 * 
	 * @return error code
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            ,
	 * @param errorMessage
	 *            error message
	 */
	public RYException(ErrorCode errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode
	 *            ,
	 * @param errorMessage
	 *            error message
	 * @param t
	 *            throwable t
	 */
	public RYException(ErrorCode errorCode, String errorMessage, Throwable t) {
		super(errorMessage, t);
		this.errorCode = errorCode;
	}

}
