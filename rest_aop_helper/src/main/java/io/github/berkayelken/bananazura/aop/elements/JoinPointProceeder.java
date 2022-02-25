package io.github.berkayelken.bananazura.aop.elements;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@FunctionalInterface
public interface JoinPointProceeder {
	Object proceed() throws Throwable;
}
