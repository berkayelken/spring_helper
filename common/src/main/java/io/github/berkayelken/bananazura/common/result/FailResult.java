package io.github.berkayelken.bananazura.common.result;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 **/
public interface FailResult<T> {
	T getFailResult();

	T getFailResult(HttpServletRequest req, BananazuraThrowable lt);

	T getInvalidArgumentResult(HttpServletRequest req, MethodArgumentNotValidException t, String errorCode);

	default T getFailResult(Object... args) {
		return getFailResult();
	}
}
