package io.github.berkayelken.bananazura.common.exception;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public class RestControllerException extends BananazuraThrowable {
	private static final long serialVersionUID = 5188329388554329950L;

	public RestControllerException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public RestControllerException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}

}
