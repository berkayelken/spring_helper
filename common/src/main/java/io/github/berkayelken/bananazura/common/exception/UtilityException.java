package io.github.berkayelken.bananazura.common.exception;

public class UtilityException extends BananazuraThrowable {
	private static final long serialVersionUID = 912936539664233342L;

	public UtilityException(String message, Throwable thrownException, Class<?> throwerClass) {
		super(message, thrownException, throwerClass);
	}

	public UtilityException(String message, Throwable thrownException, Class<?> throwerClass, String errorCode) {
		super(message, thrownException, throwerClass, errorCode);
	}
}
