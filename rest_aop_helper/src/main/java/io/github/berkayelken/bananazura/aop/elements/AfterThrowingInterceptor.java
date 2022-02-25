package io.github.berkayelken.bananazura.aop.elements;

import java.lang.reflect.Method;

import io.github.berkayelken.bananazura.aop.aspect.exception.ExceptionAspect;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
public class AfterThrowingInterceptor implements ThrowsAdvice {
	private ExceptionAspect errorLoggingAspect;
	private JoinPointProceeder joinPointProceeder;

	public AfterThrowingInterceptor(ExceptionAspect errorLoggingAspect, JoinPointProceeder joinPointProceeder) {
		this.errorLoggingAspect = errorLoggingAspect;
		this.joinPointProceeder = joinPointProceeder;
	}

	public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) throws Throwable {
		MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
		errorLoggingAspect.handleError(methodInvocation, throwable);
	}
}
