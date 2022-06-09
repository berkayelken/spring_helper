package io.github.berkayelken.bananazura.aop.aspect.logging;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public interface InfoLoggingAspect {
	void logBeforeInfo(MethodInvocation methodInvocation);

	void logAfterReturningInfo(MethodInvocation methodInvocation, Object returnValue);
}
