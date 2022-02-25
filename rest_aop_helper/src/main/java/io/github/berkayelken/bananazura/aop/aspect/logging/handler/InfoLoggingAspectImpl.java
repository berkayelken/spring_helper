package io.github.berkayelken.bananazura.aop.aspect.logging.handler;

import io.github.berkayelken.bananazura.aop.aspect.logging.InfoLoggingAspect;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
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
