package io.github.berkayelken.bananazura.common.exception;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public class UtilityException extends BananazuraThrowable {
	private static final long serialVersionUID = 912936539664233342L;

	public UtilityException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public UtilityException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}
}
