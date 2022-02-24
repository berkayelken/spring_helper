package com.bananazura.aop.configuration;

import com.bananazura.aop.aspect.exception.ModelExceptionAspect;
import com.bananazura.aop.aspect.exception.ExternalCallExceptionAspect;
import com.bananazura.aop.aspect.exception.ModelExceptionAspect;
import com.bananazura.aop.aspect.exception.RestControllerExceptionAspect;
import com.bananazura.aop.aspect.exception.ServiceExceptionAspect;
import com.bananazura.aop.aspect.exception.UtilityExceptionAspect;
import com.bananazura.aop.aspect.logging.ErrorLoggingAspect;
import com.bananazura.aop.aspect.logging.InfoLoggingAspect;
import com.bananazura.aop.annotation.EnableBananazuraExceptionHandling;
import com.bananazura.aop.annotation.EnableBananazuraLoggingHandling;
import com.bananazura.aop.annotation.EnableBananazuraRestControllerAdvice;
import com.bananazura.aop.elements.BananazuraJoinPoint;
import com.bananazura.aop.elements.BananazuraAfterThrowingAdvice;
import com.bananazura.aop.elements.JoinPointProceeder;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@EnableBananazuraExceptionHandling
@EnableBananazuraLoggingHandling
@EnableBananazuraRestControllerAdvice
@Configuration
public class AspectConfiguration {
	@Value("${bananazura.spring.basePackage}")
	private String basePackage;

	@Autowired(required = false)
	private InfoLoggingAspect infoLoggingAspect;

	@Autowired(required = false)
	private ErrorLoggingAspect errorLoggingAspect;

	@Autowired(required = false)
	private ExternalCallExceptionAspect externalCallExceptionAspect;

	@Autowired(required = false)
	private ModelExceptionAspect modelExceptionAspect;

	@Autowired(required = false)
	private RestControllerExceptionAspect restControllerExceptionAspect;

	@Autowired(required = false)
	private ServiceExceptionAspect serviceExceptionAspect;

	@Autowired(required = false)
	private UtilityExceptionAspect utilityExceptionAspect;

	@Autowired(required = false)
	private JoinPointProceeder joinPointProceeder;

	@Bean
	@ConditionalOnExpression("'${bananazura.spring.aop.log.info.before:true}' == 'true'")
	public Advisor logInfoBeforeAdvice() {
		if (infoLoggingAspect == null)
			return null;
		MethodBeforeAdvice beforeAdvice = (method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			infoLoggingAspect.logBeforeInfo(methodInvocation);
		};
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.logInfoPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);
		return new DefaultPointcutAdvisor(pointcut, beforeAdvice);
	}

	@Bean
	@ConditionalOnExpression("'${bananazura.spring.aop.log.info.after:true}' == 'true'")
	public Advisor logAfterReturningAdvice() {
		if (infoLoggingAspect == null)
			return null;

		AfterReturningAdvice beforeAdvice = (retVal, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			infoLoggingAspect.logAfterReturningInfo(methodInvocation, retVal);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.logInfoPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, beforeAdvice);
	}

	@Bean
	public Advisor logErrorAdvice() {
		if (errorLoggingAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			errorLoggingAspect.logError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.logErrorPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 1)
	@Bean
	public Advisor handleRestControllerException() {
		if (restControllerExceptionAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			restControllerExceptionAspect.handleError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.restControllerPointcut() && within(" + basePackage
				+ "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 2)
	@Bean
	public Advisor handleServiceException() {
		if (serviceExceptionAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			serviceExceptionAspect.handleError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.servicePointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 3)
	@Bean
	public Advisor handleModelException() {
		if (modelExceptionAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			modelExceptionAspect.handleError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.modelPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 4)
	@Bean
	public Advisor handleUtilityException() {
		if (utilityExceptionAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			utilityExceptionAspect.handleError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.utilityPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 5)
	@Bean
	public Advisor handleExternallCallException() {
		if (externalCallExceptionAspect == null)
			return null;

		BananazuraAfterThrowingAdvice throwsAdvice = (throwable, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			externalCallExceptionAspect.handleError(methodInvocation, throwable);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = "com.bananazura.aop.util.PointcutUtil.externalCallPointcut() && within(" + basePackage
				+ "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, throwsAdvice);
	}
}
