package com.bananazura.aop.aspect.logging.handler;

import com.bananazura.aop.aspect.logging.InfoLoggingAspect;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${bananazura.spring.aop.log.info.aspect:true}' == 'true'")
class InfoLoggingAspectImpl implements InfoLoggingAspect {
	public void logBeforeInfo(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getMethod().getName();

		StringBuilder message = new StringBuilder(methodName);
		message.append(" is started.");

		Logger logger = LoggerFactory.getLogger(methodInvocation.getMethod().getDeclaringClass());
		logger.info(message.toString());
	}

	public void logAfterReturningInfo(MethodInvocation methodInvocation, Object returnValue) {
		StringBuilder message = new StringBuilder(methodInvocation.getMethod().getName());

		message.append(" is successfully completed.");
		Logger logger = LoggerFactory.getLogger(methodInvocation.getMethod().getDeclaringClass());

		if (returnValue != null) {
			message.append(" :: returnValue={} ");
			logger.info(message.toString(), returnValue);
		} else
			logger.info(message.toString());
	}
}