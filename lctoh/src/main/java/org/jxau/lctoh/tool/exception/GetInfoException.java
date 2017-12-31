package org.jxau.lctoh.tool.exception;

public class GetInfoException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GetInfoException() {
		super();
	}

	public GetInfoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GetInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public GetInfoException(String message) {
		super(message);
	}

	public GetInfoException(Throwable cause) {
		super(cause);
	}
}
