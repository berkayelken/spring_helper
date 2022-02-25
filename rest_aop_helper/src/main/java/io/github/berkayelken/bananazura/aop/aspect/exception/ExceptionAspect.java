package io.github.berkayelken.bananazura.aop.aspect.exception;

import org.aopalliance.intercept.MethodInvocation;

interface ExceptionAspect {
	void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable;
}
