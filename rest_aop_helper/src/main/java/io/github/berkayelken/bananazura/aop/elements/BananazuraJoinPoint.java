package io.github.berkayelken.bananazura.aop.elements;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.Nullable;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class BananazuraJoinPoint implements MethodInvocation {

	@Nullable
	private final Object target;

	private final Method method;

	private Object[] arguments;

	@Nullable
	private final JoinPointProceeder joinPointProceeder;

	public BananazuraJoinPoint(@Nullable Object target, Method method, Object[] arguments,
			@Nullable JoinPointProceeder joinPointProceeder) {
		this.target = target;
		this.method = method;
		this.arguments = arguments;
		this.joinPointProceeder = joinPointProceeder;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public final Object[] getArguments() {
		return arguments;
	}

	@Override
	public Object proceed() throws Throwable {
		if (joinPointProceeder == null)
			return null;
		return joinPointProceeder.proceed();
	}

	@Override
	@Nullable
	public final Object getThis() {
		return target;
	}

	@Override
	public final AccessibleObject getStaticPart() {
		return method;
	}
}
