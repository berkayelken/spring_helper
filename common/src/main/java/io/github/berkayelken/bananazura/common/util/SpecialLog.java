package io.github.berkayelken.bananazura.common.util;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public interface SpecialLog {
	void logError(HttpServletRequest req, BananazuraThrowable lt);

	void logError(HttpServletRequest req, MethodArgumentNotValidException e);
}
