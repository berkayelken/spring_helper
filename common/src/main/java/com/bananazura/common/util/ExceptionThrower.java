package com.bananazura.common.util;

import com.bananazura.common.exception.*;

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

	public static BananazuraThrowable throwLocalizedThrowable(Class<?> throwerClass, Throwable t, String errorCode) {
		String message = throwerClass.getCanonicalName() + " is faced with unrecognized error. :: cause={}";

		return new BananazuraThrowable(message, t, throwerClass, errorCode);
	}
}
