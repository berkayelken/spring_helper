package com.bananazura.common.util;

import com.bananazura.common.exception.BananazuraThrowable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

public interface SpecialLog {
	void logError(HttpServletRequest req, BananazuraThrowable lt);

	void logError(HttpServletRequest req, MethodArgumentNotValidException e);
}
