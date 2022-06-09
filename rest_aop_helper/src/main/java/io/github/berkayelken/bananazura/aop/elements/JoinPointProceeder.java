package io.github.berkayelken.bananazura.aop.elements;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@FunctionalInterface
public interface JoinPointProceeder {
	Object proceed() throws Throwable;
}
