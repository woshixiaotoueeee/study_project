package org.jxau.lctoh.user.basis.exception;

/**
 * @author qdt_PC
 */
public class VerificationCodeException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VerificationCodeException() {
		super();
	}

	public VerificationCodeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VerificationCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerificationCodeException(String message) {
		super(message);
	}

	public VerificationCodeException(Throwable cause) {
		super(cause);
	}
}
