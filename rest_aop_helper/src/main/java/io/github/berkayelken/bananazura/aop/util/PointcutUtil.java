package io.github.berkayelken.bananazura.aop.util;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public final class PointcutUtil {

	@Pointcut("within(@io.github.berkayelken.bananazura.common.annotation.AopEvict *) || @annotation(io.github.berkayelken.bananazura.common.annotation.AopEvict) || aspectPointcut()")
	public static void aopEvictPointcut() {
	}

	@Pointcut("within(@org.aspectj.lang.annotation.Aspect *) || within(@org.springframework.web.bind.annotation.ControllerAdvice *) || within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
	private static void aspectAnnotation() {
	}

	@Pointcut("aspectAnnotation() && !within(io.github.berkayelken.bananazura.aop..*)")
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

	@Pointcut("!aopEvictPointcut() && restControllerAnnotation()")
	public static void restControllerPointcut() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	private static void repositoryAnnotation() {
	}

	@Pointcut("within(@org.springframework.stereotype.Service *) || repositoryAnnotation()")
	private static void serviceAnnotation() {
	}

	@Pointcut("!aopEvictPointcut() && serviceAnnotation()")
	public static void servicePointcut() {
	}

	@Pointcut("within(@io.github.berkayelken.bananazura.common.annotation.ExternalCall *) || @annotation(io.github.berkayelken.bananazura.common.annotation.ExternalCall)")
	private static void externalCallAnnotation() {
	}

	@Pointcut("!aopEvictPointcut() && externalCallAnnotation()")
	public static void externalCallPointcut() {
	}

	@Pointcut("within(@io.github.berkayelken.bananazura.common.annotation.ModelForAop *)")
	private static void modelAnnotation() {
	}

	@Pointcut("!aopEvictPointcut() && modelAnnotation()")
	public static void modelPointcut() {
	}

	@Pointcut("within(@io.github.berkayelken.bananazura.common.annotation.Utility *) || @annotation(io.github.berkayelken.bananazura.common.annotation.Utility)")
	private static void utilityAnnotation() {
	}

	@Pointcut("!aopEvictPointcut() && utilityAnnotation()")
	public static void utilityPointcut() {
	}

	@Pointcut("!aopEvictPointcut() && !configPointcut()")
	public static void logInfoPointcut() {
	}

	@Pointcut("!restControllerPointcut() && !servicePointcut() && !externalCallPointcut() && !modelPointcut() && !utilityPointcut()")
	public static void logErrorPointcut() {
	}
}
