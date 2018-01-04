package org.jxau.lctoh.tool.exception;

/**
 * 获取信息出现异常对应的异常类
 * @author qdt_PC
 */
public class GetInfoException  extends Exception{
	
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
