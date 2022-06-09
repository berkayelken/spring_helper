package io.github.berkayelken.bananazura.common.util;

import org.slf4j.LoggerFactory;
import io.github.berkayelken.bananazura.common.elements.BananazuraThrowableArgument;
import io.github.berkayelken.bananazura.common.exception.BananazuraExceptionBuilder;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public final class ExceptionThrower {

	private ExceptionThrower() {
	}

	private static<T extends BananazuraThrowable> BananazuraExceptionBuilder<T> createBuilder(Class<T> exceptionClass, Class<?> throwerClass,
			Throwable t, String errorCode) {
		BananazuraExceptionBuilder<T> builder = BananazuraExceptionBuilder.newBuilder(exceptionClass);

		String message = throwerClass.getCanonicalName() + " is faced with unrecognized error. :: cause={}";

		builder.setMessage(message);
		builder.setCause(t);
		builder.setThrowerClass(throwerClass);
		builder.setErrorCode(errorCode);

		return builder;
	}

	public static<T extends BananazuraThrowable> T throwBananazuraThrowable(Class<T> exceptionClass, Class<?> throwerClass,
			Throwable t, String errorCode) {
		return createBuilder(exceptionClass,throwerClass, t, errorCode).build();
	}

	public static<T extends BananazuraThrowable> T throwBananazuraThrowable(Class<T> exceptionClass, Class<?> throwerClass,
			Throwable t, String errorCode, List<BananazuraThrowableArgument<?>> arguments) {
		BananazuraExceptionBuilder<T> builder = createBuilder(exceptionClass,throwerClass, t, errorCode);

		builder.setArguments(arguments);

		return builder.build();
	}

	public static List<String> createLogFieldList(String... logField) {
		if(logField == null || logField.length == 0)
			return null;
		return Arrays.asList(logField);
	}

	public static List<BananazuraThrowableArgument<?>> createArguments(List<String> logFields, Object... args) {
		if (isArgumentsInvalid(logFields, args))
			return null;

		List<BananazuraThrowableArgument<?>> arguments = new ArrayList<>();

		for(int i = 0; i < args.length; i++) {
			arguments.add(new BananazuraThrowableArgument<>(args[i], logFields.get(i)));
		}

		return arguments;
	}

	private static boolean isArgumentsInvalid(List<String> logFields, Object... args) {
		if(logFields == null || logFields.size() == 0) {
			LoggerFactory.getLogger(ExceptionThrower.class).warn("BananazuraThrowableArgument cannot be created. " +
					"Because logFields is empty.");
			return true;
		}

		if(args == null || args.length == 0) {
			LoggerFactory.getLogger(ExceptionThrower.class).warn("BananazuraThrowableArgument cannot be created. " +
					"Because args is empty.");
			return true;
		}

		if(logFields.size() != args.length) {
			LoggerFactory.getLogger(ExceptionThrower.class).warn("BananazuraThrowableArgument cannot be created. " +
					"Because logFields and args sizes are not equal.");
			return true;
		}

		return false;
	}
}
