package io.github.berkayelken.bananazura.common.exception;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import io.github.berkayelken.bananazura.common.annotation.ErrorCode;
import io.github.berkayelken.bananazura.common.elements.BananazuraThrowableArgument;

import lombok.Getter;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Getter
public class BananazuraExceptionBuilder<T extends BananazuraThrowable>  {
	private Class<T> exceptionClass;
	private String message;
	private Throwable cause;
	private Class<?> throwerClass;
	private String errorCode;
	private ErrorCode errorCodeAnnotation;
	private List<BananazuraThrowableArgument<?>> arguments = new ArrayList<>();

	private BananazuraExceptionBuilder(Class<T> exceptionClass) {
		setExceptionClass(exceptionClass);
	}

	public static<T extends BananazuraThrowable>  BananazuraExceptionBuilder<T> newBuilder(Class<T> exceptionClass) {
		return new BananazuraExceptionBuilder<>(exceptionClass);
	}

	private void setExceptionClass(Class<T> exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public BananazuraExceptionBuilder<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public BananazuraExceptionBuilder<T> setCause(Throwable cause) {
		this.cause = cause;
		return this;
	}

	public BananazuraExceptionBuilder<T> setThrowerClass(Class<?> throwerClass) {
		this.throwerClass = throwerClass;
		return this;
	}

	public BananazuraExceptionBuilder<T> setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public BananazuraExceptionBuilder<T> setErrorCodeAnnotation(ErrorCode errorCodeAnnotation) {
		this.errorCodeAnnotation = errorCodeAnnotation;
		return this;
	}

	public <R> BananazuraExceptionBuilder<T> addArgument(R argument, String logField) {
		this.arguments.add(new BananazuraThrowableArgument<>(argument, logField));
		return this;
	}

	public BananazuraExceptionBuilder<T> addArgument(BananazuraThrowableArgument<?> argument) {
		this.arguments.add(argument);
		return this;
	}

	public BananazuraExceptionBuilder<T> setArguments(List<BananazuraThrowableArgument<?>> arguments) {
		this.arguments = arguments;
		return this;
	}

	public T build() {
		try {
			Constructor<T> constructor = exceptionClass.getDeclaredConstructor(getClass());
			constructor.setAccessible(true);
			return constructor.newInstance(this);
		} catch (Throwable e) {
			return null;
		}
	}


}
