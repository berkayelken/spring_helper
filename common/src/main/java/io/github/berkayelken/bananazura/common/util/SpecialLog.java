package io.github.berkayelken.bananazura.common.util;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * @createdOn 	: 25-02-2022
 * @project 	: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface SpecialLog {
	void logError(HttpServletRequest req, BananazuraThrowable lt);

	void logError(HttpServletRequest req, MethodArgumentNotValidException e);
}
