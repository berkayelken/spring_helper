package io.github.berkayelken.bananazura.aop.util;

import java.util.stream.Stream;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 02-03-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public final class LoggingUtil {

	private LoggingUtil() {

	}

	public static void handleErrorLog(MethodInvocation methodInvocation, Throwable t, boolean printArguments) {
		StringBuilder message = new StringBuilder(methodInvocation.getMethod().getName());
		message.append(" is completed with exception. ");

		printErrorLog(message, methodInvocation.getMethod().getDeclaringClass(),
				printArguments, t, methodInvocation.getArguments());
	}

	public static void printBeforeLog(StringBuilder message, Class<?> declaringClass, boolean printArguments, Object... args) {
		Logger logger = LoggerFactory.getLogger(declaringClass);
		if(args == null || !printArguments){
			logger.info(message.toString());
		} else {
			addArgumentsToLogMessage(message, args);
			logger.info(message.toString(), args);
		}
	}

	 public static void printErrorLog(StringBuilder message, Class<?> declaringClass, boolean printArguments, Throwable t, Object... args) {
		Logger logger = LoggerFactory.getLogger(declaringClass);
		if(args == null || !printArguments){
			logger.error(message.toString(), t);
		} else {
			addArgumentsToLogMessage(message, args);
			message.append(" :: exception={} ");
			logger.error(message.toString(), handleErrorArgs(t, args));
		}
	}

	private static void addArgumentsToLogMessage(StringBuilder message, Object[] args) {
		for(int i = 0; i < args.length; i++) {
			message.append(" :: arg" + i + "={} ");
		}
	}

	private static Object[] handleErrorArgs(Throwable t, Object... args) {
		return Stream.concat(Stream.of(args), Stream.of(t)).toArray(Object[]::new);
	}
}
