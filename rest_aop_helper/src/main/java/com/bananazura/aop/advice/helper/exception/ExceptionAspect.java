package com.bananazura.aop.advice.helper.exception;

import org.aopalliance.intercept.MethodInvocation;

interface ExceptionAspect {
	void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable;
}
