package com.bananazura.common.exception;

public class ServiceException extends UtilityException {
	private static final long serialVersionUID = -1059051852271794147L;

	public ServiceException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public ServiceException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}
}