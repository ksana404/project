package com.sb.exception;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3976848312412779226L;

	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	} 
}
