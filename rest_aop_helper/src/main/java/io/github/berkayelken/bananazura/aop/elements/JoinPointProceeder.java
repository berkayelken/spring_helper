package io.github.berkayelken.bananazura.aop.elements;

@FunctionalInterface
public interface JoinPointProceeder {
	Object proceed() throws Throwable;
}
