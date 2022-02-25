package io.github.berkayelken.bananazura.common.result;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface Result<Success, Fail> extends SuccessResult<Success>, FailResult<Fail>{

}
