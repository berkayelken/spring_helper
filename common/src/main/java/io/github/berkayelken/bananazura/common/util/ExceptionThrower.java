package io.github.berkayelken.bananazura.common.util;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.ExternalCallException;
import io.github.berkayelken.bananazura.common.exception.ModelException;
import io.github.berkayelken.bananazura.common.exception.RestControllerException;
import io.github.berkayelken.bananazura.common.exception.ServiceException;
import io.github.berkayelken.bananazura.common.exception.UtilityException;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public final class ExceptionThrower {

	private ExceptionThrower() {
	}

	public static ServiceException throwServiceException(Class<?> throwerClass, Throwable t, String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with error. :: cause={}";

		return new ServiceException(message, t, throwerClass, errorCode);
	}

	public static ModelException throwModelException(Class<?> throwerClass, Throwable t, String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with error. :: cause={}";

		return new ModelException(message, t, throwerClass, errorCode);
	}

	public static UtilityException throwUtilityException(Class<?> throwerClass, Throwable t, String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with error. :: cause={}";

		return new UtilityException(message, t, throwerClass, errorCode);
	}

	public static ExternalCallException throwExternalCallException(Class<?> throwerClass, Throwable t,
			String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with error. :: cause={}";

		return new ExternalCallException(message, t, throwerClass, errorCode);
	}

	public static RestControllerException throwRestControllerException(Class<?> throwerClass, Throwable t,
			String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with unrecognized error. :: cause={}";

		return new RestControllerException(message, t, throwerClass, errorCode);
	}

	public static BananazuraThrowable throwBananazuraThrowable(Class<?> throwerClass, Throwable t, String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with unrecognized error. :: cause={}";

		return new BananazuraThrowable(message, t, throwerClass, errorCode);
	}
}
