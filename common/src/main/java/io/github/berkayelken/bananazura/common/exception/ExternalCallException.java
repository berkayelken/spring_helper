package io.github.berkayelken.bananazura.common.exception;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public class ExternalCallException extends BananazuraThrowable {
	private static final long serialVersionUID = -2056627769166263559L;

	public ExternalCallException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public ExternalCallException(String message, Throwable cause, Class<?> throwerClass, String errorCode) {
		super(message, cause, throwerClass, errorCode);
	}
}
