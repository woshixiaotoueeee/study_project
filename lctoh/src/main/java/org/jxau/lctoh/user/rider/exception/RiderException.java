package org.jxau.lctoh.user.rider.exception;

/**
 * @author qdt_PC
 *
 */
public class RiderException extends Exception {
	

	public RiderException() {
		super();
	}

	public RiderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RiderException(String message, Throwable cause) {
		super(message, cause);
	}

	public RiderException(String message) {
		super(message);
	}

	public RiderException(Throwable cause) {
		super(cause);
	}
}
