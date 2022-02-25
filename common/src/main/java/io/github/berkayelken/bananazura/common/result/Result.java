package io.github.berkayelken.bananazura.common.result;

import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

public interface Result<Success, Fail> {
	Success getSuccessResult();

	default Success getSuccessResult(Object... args) {
		return getSuccessResult();
	}

	Fail getFailResult();

	Fail getFailResult(HttpServletRequest req, BananazuraThrowable lt);

	Fail getInvalidArgumentResult(HttpServletRequest req, MethodArgumentNotValidException t, String errorCode);

	default Fail getFailResult(Object... args) {
		return getFailResult();
	}
}
