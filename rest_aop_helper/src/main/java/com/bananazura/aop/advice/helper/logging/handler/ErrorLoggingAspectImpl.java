package com.bananazura.aop.advice.helper.logging.handler;

import static com.bananazura.aop.util.AspectUtil.handleError;

import com.bananazura.aop.advice.helper.logging.ErrorLoggingAspect;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${bananazura.spring.aop.log.exception.aspect:true}' == 'true'")
class ErrorLoggingAspectImpl implements ErrorLoggingAspect {
	public void logError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		handleError(methodInvocation, t);
		throw t;
	}
}
