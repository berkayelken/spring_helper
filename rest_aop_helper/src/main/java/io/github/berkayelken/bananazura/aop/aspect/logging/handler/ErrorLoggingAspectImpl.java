package io.github.berkayelken.bananazura.aop.aspect.logging.handler;

import io.github.berkayelken.bananazura.aop.aspect.logging.ErrorLoggingAspect;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import static io.github.berkayelken.bananazura.aop.util.LoggingUtil.handleErrorLog;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Service
@ConditionalOnExpression("'${bananazura.spring.aop.log.exception.aspect:true}' == 'true'")
class ErrorLoggingAspectImpl implements ErrorLoggingAspect {
	@Value("${bananazura.spring.aop.log.info.printarguments:true}")
	private boolean printArguments;

	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		handleErrorLog(methodInvocation, t, printArguments);
		throw t;
	}
}
