package io.github.berkayelken.bananazura.common.result;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface SuccessResult<T> {
	T getSuccessResult();

	default T getSuccessResult(Object... args) {
		return getSuccessResult();
	}
}
