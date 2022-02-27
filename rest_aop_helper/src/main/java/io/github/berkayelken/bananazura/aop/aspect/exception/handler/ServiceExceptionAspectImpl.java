package io.github.berkayelken.bananazura.aop.aspect.exception.handler;

import static io.github.berkayelken.bananazura.aop.util.AspectUtil.getErrorCode;
import static io.github.berkayelken.bananazura.aop.util.AspectUtil.handleAndGetErrorCode;
import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwBananazuraThrowable;

import io.github.berkayelken.bananazura.aop.aspect.exception.ServiceExceptionAspect;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.ServiceException;
import io.github.berkayelken.bananazura.common.properties.AppProperties;
import io.github.berkayelken.bananazura.aop.util.AspectUtil;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Service
@ConditionalOnExpression("'${bananazura.spring.aop.service.exception.aspect:true}' == 'true'")
class ServiceExceptionAspectImpl implements ServiceExceptionAspect {
	@Autowired
	private AppProperties appConf;

	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		if (t instanceof BananazuraThrowable)
			throw t;
		String methodName = methodInvocation.getMethod().getName();
		Class<?> throwerClass = methodInvocation.getMethod().getDeclaringClass();

		AspectUtil.handleError(methodName, throwerClass, t);

		String errorCode = getErrorCode(methodInvocation, appConf.getServiceErrorCode(), appConf.getDefaultErrorCode());

		BananazuraThrowable ex = throwBananazuraThrowable(ServiceException.class, throwerClass, t, errorCode);
		ex.setErrorCodeAnnotation(handleAndGetErrorCode(methodInvocation));

		throw ex;
	}

}
