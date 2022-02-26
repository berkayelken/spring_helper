package io.github.berkayelken.bananazura.aop.aspect.logging;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public interface InfoLoggingAspect {
	void logBeforeInfo(MethodInvocation methodInvocation);

	void logAfterReturningInfo(MethodInvocation methodInvocation, Object returnValue);
}
