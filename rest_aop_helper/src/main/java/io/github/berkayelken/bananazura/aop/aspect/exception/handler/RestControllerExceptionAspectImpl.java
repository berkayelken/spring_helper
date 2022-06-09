package io.github.berkayelken.bananazura.aop.aspect.exception.handler;

import static io.github.berkayelken.bananazura.aop.util.LoggingUtil.handleErrorLog;
import static io.github.berkayelken.bananazura.common.util.ErrorCodeUtil.getErrorCode;
import static io.github.berkayelken.bananazura.common.util.ErrorCodeUtil.handleAndGetErrorCode;
import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwBananazuraThrowable;

import io.github.berkayelken.bananazura.aop.aspect.exception.RestControllerExceptionAspect;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.RestControllerException;
import io.github.berkayelken.bananazura.common.properties.AppProperties;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Service
@ConditionalOnExpression("'${bananazura.spring.aop.restcontroller.exception.aspect:true}' == 'true'")
class RestControllerExceptionAspectImpl implements RestControllerExceptionAspect {
	@Value("${bananazura.spring.aop.log.info.printarguments:true}")
	private boolean printArguments;

	@Autowired
	private AppProperties appConf;

	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		if (t instanceof BananazuraThrowable)
			throw t;

		Class<?> throwerClass = methodInvocation.getMethod().getDeclaringClass();

		handleErrorLog(methodInvocation, t, printArguments);

		String errorCode = getErrorCode(methodInvocation, appConf.getRestControllerErrorCode(),
				appConf.getDefaultErrorCode());

		BananazuraThrowable ex = throwBananazuraThrowable(RestControllerException.class, throwerClass, t, errorCode);
		ex.setErrorCodeAnnotation(handleAndGetErrorCode(methodInvocation));

		throw ex;
	}

}
