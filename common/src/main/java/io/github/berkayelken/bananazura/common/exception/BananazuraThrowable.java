package io.github.berkayelken.bananazura.common.exception;

import io.github.berkayelken.bananazura.common.annotation.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Getter
@Setter
public class BananazuraThrowable extends RuntimeException {
	private static final long serialVersionUID = 7958477276702218868L;

	private Class<?> throwerClass;
	private String errorCode;
	private ErrorCode errorCodeAnnotation;

	public BananazuraThrowable(String message, Throwable cause, Class<?> throwerClass) {
		super(message, cause);
		setThrowerClass(throwerClass);
	}

	public BananazuraThrowable(String message, Throwable cause, Class<?> throwerClass, String errorCode) {
		this(message, cause, throwerClass);
		setErrorCode(errorCode);
	}
}
