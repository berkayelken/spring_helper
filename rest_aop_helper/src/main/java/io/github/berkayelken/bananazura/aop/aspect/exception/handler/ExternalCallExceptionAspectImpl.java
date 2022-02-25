package io.github.berkayelken.bananazura.aop.aspect.exception.handler;

import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwExternalCallException;

import io.github.berkayelken.bananazura.aop.aspect.exception.ExternalCallExceptionAspect;
import io.github.berkayelken.bananazura.aop.util.AspectUtil;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.properties.AppProperties;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${bananazura.spring.aop.extcall.exception.aspect:true}' == 'true'")
class ExternalCallExceptionAspectImpl implements ExternalCallExceptionAspect {
	@Autowired
	private AppProperties appConf;

	public void handleError(MethodInvocation methodInvocation, Throwable t) throws Throwable {
		if (t instanceof BananazuraThrowable)
			throw t;
		String methodName = methodInvocation.getMethod().getName();
		Class<?> throwerClass = methodInvocation.getMethod().getDeclaringClass();

		AspectUtil.handleError(methodName, throwerClass, t);

		String errorCode = AspectUtil.getErrorCode(methodInvocation, appConf.getExternalCallErrorCode(),
				appConf.getDefaultErrorCode());

		BananazuraThrowable ex = throwExternalCallException(throwerClass, t, errorCode);
		ex.setErrorCodeAnnotation(AspectUtil.handleAndGetErrorCode(methodInvocation));

		throw ex;
	}
}
