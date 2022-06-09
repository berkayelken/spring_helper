package io.github.berkayelken.bananazura.common.result;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface SuccessResult<T> {
	T getSuccessResult();

	T getSuccessResult(Object... args);
}
