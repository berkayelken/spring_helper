package io.github.berkayelken.bananazura.aop.aspect.exception;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public interface ExceptionAspect {
	void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable;
}
