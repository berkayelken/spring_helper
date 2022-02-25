package io.github.berkayelken.bananazura.common.exception;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public class ModelException extends UtilityException {
	private static final long serialVersionUID = 4180370731701806842L;

	public ModelException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public ModelException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}

}