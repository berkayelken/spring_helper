package com.bananazura.aop.advice.helper.exception.handler;

import static com.bananazura.aop.util.AspectUtil.getErrorCode;
import static com.bananazura.aop.util.AspectUtil.handleAndGetErrorCode;
import static com.bananazura.common.util.ExceptionThrower.throwServiceException;

import com.bananazura.aop.advice.helper.exception.ServiceExceptionAspect;
import com.bananazura.common.exception.BananazuraThrowable;
import com.bananazura.common.properties.AppProperties;
import com.bananazura.aop.util.AspectUtil;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

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

		BananazuraThrowable ex = throwServiceException(throwerClass, t, errorCode);
		ex.setErrorCodeAnnotation(handleAndGetErrorCode(methodInvocation));

		throw ex;
	}

}
