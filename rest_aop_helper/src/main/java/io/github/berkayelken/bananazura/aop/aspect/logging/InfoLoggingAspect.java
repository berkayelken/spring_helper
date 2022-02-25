package io.github.berkayelken.bananazura.aop.aspect.logging;

import org.aopalliance.intercept.MethodInvocation;

public interface InfoLoggingAspect {
	void logBeforeInfo(MethodInvocation methodInvocation);

	void logAfterReturningInfo(MethodInvocation methodInvocation, Object returnValue);
}
