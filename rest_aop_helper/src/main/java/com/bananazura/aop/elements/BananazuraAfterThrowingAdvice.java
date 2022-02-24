package com.bananazura.aop.elements;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.lang.Nullable;

@FunctionalInterface
public interface BananazuraAfterThrowingAdvice extends ThrowsAdvice {
	void afterReturning(Throwable throwable, Method method, Object[] args, @Nullable Object target) throws Throwable;
}
