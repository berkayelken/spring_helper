package io.github.berkayelken.bananazura.common.util;

import io.github.berkayelken.bananazura.common.annotation.ErrorCode;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isBlank;
import static io.github.berkayelken.bananazura.common.util.CommonUtil.isNotBlank;

public final class ErrorCodeUtil {
	private ErrorCodeUtil() {

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

	private static int getErrorCodeIndex(ErrorCode errorCode, Throwable t) {
		try {
			for (int i = 0; i < errorCode.errors().length; i++) {
				if (errorCode.errors()[i].isInstance(t))
					return i;
			}
		} catch (Throwable t1) {

		}

		return -1;
	}

	public static String getErrorCode(String errorCode, ErrorCode errorCodeAnno, Throwable t) {
		int index = getErrorCodeIndex(errorCodeAnno, t);
		if (index != -1)
			return errorCodeAnno.errorCodes()[index];
		if (errorCodeAnno != null) {
			if (isNotBlank(errorCodeAnno.code()))
				return errorCodeAnno.code();
			return errorCodeAnno.value();
		}

		return errorCode;
	}

	public static String handleAndGetText(String text, ErrorCode errorCode, Throwable t) {
		int index = getErrorCodeIndex(errorCode, t);
		boolean needReplacement = errorCode != null && errorCode.replacePlaceholder()
				&& errorCode.placeholder() != null && errorCode.placeholderIndexer().length > index
				&& errorCode.placeholder().length > index && isNotBlank(t.getMessage());

		if (!needReplacement)
			return text;

		int replacementCount = errorCode.placeholderIndexer()[index];
		int skip = Arrays.stream(errorCode.placeholderIndexer()).limit(index).sum();

		List<String> placeholders = Arrays.stream(errorCode.placeholder()).skip(skip).limit(replacementCount)
				.collect(Collectors.toList());

		if (t.getMessage().contains(";"))
			return replacePlaceholder(text, placeholders, t.getMessage().split(";"));

		return replacePlaceholder(text, placeholders, t.getMessage());
	}

	private static String replacePlaceholder(String text, List<String> placeholders, String... replacer) {
		for (int i = 0; i < placeholders.size(); i++) {
			text = text.replaceAll(placeholders.get(i), replacer[i]);
		}

		return text;
	}
}
