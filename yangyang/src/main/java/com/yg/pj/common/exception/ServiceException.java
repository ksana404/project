package com.yg.pj.common.exception;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5463841022677485369L;

	public ServiceException() {
		super();
	}
	/**抛出异常提示信息*/
	public ServiceException(String message) {
		super(message);
	}
	
	/*抛出异常？*/
	public ServiceException(Throwable cause) {
		super(cause);
	}
		
	/*
	 说明：几乎在所有的框架中都提供了自定义异常，
	 例如MyBatis中的BindingException等。
	 * */
}
