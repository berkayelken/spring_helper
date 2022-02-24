package com.bananazura.aop.util;

import org.aspectj.lang.annotation.Pointcut;

public final class PointcutUtil {

	@Pointcut("within(@com.bananazura.common.annotation.AopEvict *) || @annotation(com.bananazura.common.annotation.AopEvict) || aspectPointcut()")
	public static void aopEvictPointcut() {
	}

	@Pointcut("within(@org.aspectj.lang.annotation.Aspect *) || within(@org.springframework.web.bind.annotation.ControllerAdvice *) || within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
	private static void aspectAnnotation() {
	}

	@Pointcut("aspectAnnotation() && !within(com.bananazura.aop..*)")
	public static void aspectPointcut() {
	}

	@Pointcut("within(@org.springframework.boot.context.properties.ConfigurationProperties *) || within(@org.springframework.context.annotation.Configuration *)")
	private static void configAnnotation() {
	}

	@Pointcut("configAnnotation()")
	public static void configPointcut() {
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	private static void restControllerAnnotation() {
	}

	@Pointcut("restControllerAnnotation()")
	public static void restControllerPointcut() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	private static void repositoryAnnotation() {
	}

	@Pointcut("within(@org.springframework.stereotype.Service *) || repositoryAnnotation()")
	private static void serviceAnnotation() {
	}

	@Pointcut("serviceAnnotation()")
	public static void servicePointcut() {
	}

	@Pointcut("within(@com.bananazura.common.annotation.ExternalCall *) || @annotation(com.bananazura.common.annotation.ExternalCall)")
	private static void externalCallAnnotation() {
	}

	@Pointcut("externalCallAnnotation()")
	public static void externalCallPointcut() {
	}

	@Pointcut("within(@com.bananazura.common.annotation.ModelForAop *)")
	private static void modelAnnotation() {
	}

	@Pointcut("modelAnnotation()")
	public static void modelPointcut() {
	}

	@Pointcut("within(@com.bananazura.common.annotation.Utility *) || @annotation(com.bananazura.common.annotation.Utility)")
	private static void utilityAnnotation() {
	}

	@Pointcut("utilityAnnotation()")
	public static void utilityPointcut() {
	}

	@Pointcut("!aopEvictPointcut() && !configPointcut()")
	public static void logInfoPointcut() {
	}

	@Pointcut("!aopEvictPointcut() && !restControllerPointcut() && !servicePointcut() && !externalCallPointcut() && !modelPointcut() && !utilityPointcut()")
	public static void logErrorPointcut() {
	}
}
