package io.github.berkayelken.bananazura.aop.aspect.exception.handler;

import static io.github.berkayelken.bananazura.aop.util.LoggingUtil.handleErrorLog;
import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwBananazuraThrowable;

import io.github.berkayelken.bananazura.aop.aspect.exception.ModelExceptionAspect;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.ModelException;
import io.github.berkayelken.bananazura.common.properties.AppProperties;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Service
@ConditionalOnExpression("'${bananazura.spring.aop.model.exception.aspect:true}' == 'true'")
class ModelExceptionAspectImpl implements ModelExceptionAspect {
	@Value("${bananazura.spring.aop.log.info.printarguments:true}")
	private boolean printArguments;

	@Autowired
	private AppProperties appConf;

	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		if (t instanceof BananazuraThrowable)
			throw t;

		Class<?> throwerClass = methodInvocation.getMethod().getDeclaringClass();

		handleErrorLog(methodInvocation, t, printArguments);

		String errorCode = getErrorCode(methodInvocation, appConf.getModelErrorCode(),
				appConf.getDefaultErrorCode());

		BananazuraThrowable ex = throwBananazuraThrowable(ModelException.class, throwerClass, t, errorCode);
		ex.setErrorCodeAnnotation(handleAndGetErrorCode(methodInvocation));

		throw ex;
	}

}
