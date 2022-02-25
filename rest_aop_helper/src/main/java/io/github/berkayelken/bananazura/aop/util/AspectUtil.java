package io.github.berkayelken.bananazura.aop.util;

import io.github.berkayelken.bananazura.common.annotation.ErrorCode;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public final class AspectUtil {

	public static void handleError(MethodInvocation methodInvocation, Throwable t) {
		String methodName = methodInvocation.getMethod().getName();

		handleError(methodName, methodInvocation.getMethod().getDeclaringClass(), t);
	}

	public static void handleError(String methodName, Class<?> classObj, Throwable t) {
		handleError(methodName, classObj.getName(), t);
	}

	public static void handleError(String methodName, String className, Throwable t) {
		StringBuilder message = new StringBuilder(methodName);
		message.append(" is successfully completed. ");

		Logger logger = LoggerFactory.getLogger(className);

		logger.error(message.toString(), t);
	}

	public static String getErrorCode(MethodInvocation methodInvocation, String exceptionErrorCode,
			String defaultErrorCode) {
		try {
			return getErrorCode(methodInvocation).code();
		} catch (Throwable t) {
			return getErrorCodeWithoutVfError(exceptionErrorCode, defaultErrorCode);
		}
	}

	public static ErrorCode handleAndGetErrorCode(MethodInvocation methodInvocation) {
		ErrorCode vfErrorCode = getErrorCode(methodInvocation);

		if (vfErrorCode == null)
			return null;

		boolean isErrorArrBlank = vfErrorCode.errorCodes() == null || vfErrorCode.errorCodes().length == 0;
		boolean isExClassArrBlank = vfErrorCode.errors() == null || vfErrorCode.errors().length == 0;

		boolean multiError = !isErrorArrBlank && !isExClassArrBlank;
		boolean isCodeBlank = isBlank(vfErrorCode.code()) && isBlank(vfErrorCode.value());

		if (isCodeBlank && !multiError)
			return null;

		return vfErrorCode;
	}

	private static ErrorCode getErrorCode(MethodInvocation methodInvocation) {
		try {
			return methodInvocation.getMethod().getAnnotation(ErrorCode.class);
		} catch (Throwable t) {
			return null;
		}
	}

	public static String getErrorCodeWithoutVfError(String exceptionErrorCode, String defaultErrorCode) {
		if (isNotBlank(exceptionErrorCode))
			return exceptionErrorCode;
		return defaultErrorCode;
	}

	private static boolean isBlank(String str) {
		int strLen;

		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}

		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

}
