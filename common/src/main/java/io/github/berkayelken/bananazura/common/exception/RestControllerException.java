package io.github.berkayelken.bananazura.common.exception;

public class RestControllerException extends BananazuraThrowable {
	private static final long serialVersionUID = 5188329388554329950L;

	public RestControllerException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public RestControllerException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}

}
