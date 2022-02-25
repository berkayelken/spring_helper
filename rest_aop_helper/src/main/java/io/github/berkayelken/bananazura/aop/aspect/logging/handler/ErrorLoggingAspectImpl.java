package io.github.berkayelken.bananazura.aop.aspect.logging.handler;

import io.github.berkayelken.bananazura.aop.aspect.logging.ErrorLoggingAspect;
import io.github.berkayelken.bananazura.aop.util.AspectUtil;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Service
@ConditionalOnExpression("'${bananazura.spring.aop.log.exception.aspect:true}' == 'true'")
class ErrorLoggingAspectImpl implements ErrorLoggingAspect {
	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		AspectUtil.handleError(methodInvocation, t);
		throw t;
	}
}
