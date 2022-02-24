package com.bananazura.aop.advice.helper.logging;

import org.aopalliance.intercept.MethodInvocation;

public interface InfoLoggingAspect {
	void logBeforeInfo(MethodInvocation methodInvocation);

	void logAfterReturningInfo(MethodInvocation methodInvocation, Object returnValue);
}
