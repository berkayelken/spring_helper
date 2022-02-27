package io.github.berkayelken.bananazura.aop.configuration;

import io.github.berkayelken.bananazura.aop.aspect.exception.ModelExceptionAspect;
import io.github.berkayelken.bananazura.aop.aspect.exception.ExternalCallExceptionAspect;
import io.github.berkayelken.bananazura.aop.aspect.exception.RestControllerExceptionAspect;
import io.github.berkayelken.bananazura.aop.aspect.exception.ServiceExceptionAspect;
import io.github.berkayelken.bananazura.aop.aspect.exception.UtilityExceptionAspect;
import io.github.berkayelken.bananazura.aop.aspect.logging.ErrorLoggingAspect;
import io.github.berkayelken.bananazura.aop.aspect.logging.InfoLoggingAspect;
import io.github.berkayelken.bananazura.aop.annotation.EnableBananazuraExceptionHandling;
import io.github.berkayelken.bananazura.aop.annotation.EnableBananazuraLoggingHandling;
import io.github.berkayelken.bananazura.aop.annotation.EnableBananazuraRestControllerAdvice;
import io.github.berkayelken.bananazura.aop.elements.AfterThrowingInterceptor;
import io.github.berkayelken.bananazura.aop.elements.BananazuraJoinPoint;
import io.github.berkayelken.bananazura.aop.elements.JoinPointProceeder;
import io.github.berkayelken.bananazura.aop.util.PointcutUtil;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
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

	private static final String POINTCUT_PATH = PointcutUtil.class.getName();

	@Bean
	@ConditionalOnBean(InfoLoggingAspect.class)
	@ConditionalOnExpression("'${bananazura.spring.aop.log.info.before:true}' == 'true'")
	public Advisor logInfoBeforeAdvice() {
		MethodBeforeAdvice beforeAdvice = (method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			infoLoggingAspect.logBeforeInfo(methodInvocation);
		};
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".logInfoPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);
		return new DefaultPointcutAdvisor(pointcut, beforeAdvice);
	}

	@Bean
	@ConditionalOnBean(InfoLoggingAspect.class)
	@ConditionalOnExpression("'${bananazura.spring.aop.log.info.after:true}' == 'true'")
	public Advisor logAfterReturningAdvice() {
		AfterReturningAdvice beforeAdvice = (retVal, method, args, target) -> {
			MethodInvocation methodInvocation = new BananazuraJoinPoint(target, method, args, joinPointProceeder);
			infoLoggingAspect.logAfterReturningInfo(methodInvocation, retVal);
		};

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".logInfoPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, beforeAdvice);
	}

	@Bean
	@ConditionalOnBean(ErrorLoggingAspect.class)
	public Advisor logErrorAdvice() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(errorLoggingAspect, joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".logErrorPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 1)
	@Bean
	@ConditionalOnBean(ExternalCallExceptionAspect.class)
	public Advisor handleRestControllerException() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(restControllerExceptionAspect,
				joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".restControllerPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 2)
	@Bean
	@ConditionalOnBean(ServiceExceptionAspect.class)
	public Advisor handleServiceException() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(serviceExceptionAspect, joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".servicePointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 3)
	@Bean
	@ConditionalOnBean(ModelExceptionAspect.class)
	public Advisor handleModelException() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(modelExceptionAspect, joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".modelPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 4)
	@Bean
	@ConditionalOnBean(UtilityExceptionAspect.class)
	public Advisor handleUtilityException() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(utilityExceptionAspect, joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".utilityPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 5)
	@Bean
	@ConditionalOnBean(ExternalCallExceptionAspect.class)
	public Advisor handleExternalCallException() {
		ThrowsAdvice afterThrowingAdvice = new AfterThrowingInterceptor(externalCallExceptionAspect,
				joinPointProceeder);

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		String expression = POINTCUT_PATH + ".externalCallPointcut() && within(" + basePackage + "..*)";
		pointcut.setExpression(expression);

		return new DefaultPointcutAdvisor(pointcut, afterThrowingAdvice);
	}
}
