package io.github.berkayelken.bananazura.common.exception;

import io.github.berkayelken.bananazura.common.annotation.ErrorCode;
import io.github.berkayelken.bananazura.common.elements.BananazuraThrowableArgument;
import lombok.Getter;

import java.util.List;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Getter
public class BananazuraThrowable extends RuntimeException {
	private static final long serialVersionUID = 7958477276702218868L;

	private Class<?> throwerClass;
	private String errorCode;
	private ErrorCode errorCodeAnnotation;
	private List<BananazuraThrowableArgument<?>> arguments;

	BananazuraThrowable(BananazuraExceptionBuilder<? extends BananazuraThrowable> builder) {
		super(builder.getMessage(), builder.getCause());
		setThrowerClass(builder.getThrowerClass());
		setErrorCode(builder.getErrorCode());
		setErrorCodeAnnotation(builder.getErrorCodeAnnotation());
		setArguments(builder.getArguments());
	}

	private void setThrowerClass(Class<?> throwerClass) {
		this.throwerClass = throwerClass;
	}

	private void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorCodeAnnotation(ErrorCode errorCodeAnnotation) {
		this.errorCodeAnnotation = errorCodeAnnotation;
	}

	private void setArguments(List<BananazuraThrowableArgument<?>> arguments) {
		this.arguments = arguments;
	}
}
