package com.bananazura.aop.elements;

@FunctionalInterface
public interface JoinPointProceeder {
	Object proceed() throws Throwable;
}
