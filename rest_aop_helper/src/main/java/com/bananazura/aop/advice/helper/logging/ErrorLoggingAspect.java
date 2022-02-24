package com.bananazura.aop.advice.helper.logging;

import org.aopalliance.intercept.MethodInvocation;

public interface ErrorLoggingAspect {
	void logError(MethodInvocation methodInvocation, Throwable t) throws Throwable;
}
