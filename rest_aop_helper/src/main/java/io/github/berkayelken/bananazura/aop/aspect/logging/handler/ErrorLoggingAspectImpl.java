package io.github.berkayelken.bananazura.aop.aspect.logging.handler;

import static io.github.berkayelken.bananazura.aop.util.AspectUtil.handleError;

import io.github.berkayelken.bananazura.aop.aspect.logging.ErrorLoggingAspect;

import io.github.berkayelken.bananazura.aop.util.AspectUtil;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${bananazura.spring.aop.log.exception.aspect:true}' == 'true'")
class ErrorLoggingAspectImpl implements ErrorLoggingAspect {
	public void logError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		AspectUtil.handleError(methodInvocation, t);
		throw t;
	}
}
