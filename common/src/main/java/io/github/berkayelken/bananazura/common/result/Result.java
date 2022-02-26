package io.github.berkayelken.bananazura.common.result;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface Result<Success, Fail> extends SuccessResult<Success>, FailResult<Fail>{

}
