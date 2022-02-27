package io.github.berkayelken.bananazura.aop.advice;

import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;

import io.github.berkayelken.bananazura.aop.util.AspectUtil;
import io.github.berkayelken.bananazura.common.properties.AppProperties;
import io.github.berkayelken.bananazura.common.result.FailResult;
import io.github.berkayelken.bananazura.common.util.SpecialLog;
import io.github.berkayelken.bananazura.common.exception.ExternalCallException;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.ModelException;
import io.github.berkayelken.bananazura.common.exception.RestControllerException;
import io.github.berkayelken.bananazura.common.exception.ServiceException;
import io.github.berkayelken.bananazura.common.exception.UtilityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Berkay Yelken (https://github.com/berkayelken)
 * Date : 25-02-2022
 * Project : Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {
	@Autowired
	private AppProperties appConf;

	@Autowired
	private FailResult<?> resultUtil;

	@Autowired(required = false)
	private SpecialLog specialLog;

	@ExceptionHandler({ ServiceException.class })
	public ResponseEntity<?> handleServiceException(HttpServletRequest req, ServiceException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ ModelException.class })
	public ResponseEntity<?> handleModelException(HttpServletRequest req, ModelException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ UtilityException.class })
	public ResponseEntity<?> handleUtilityException(HttpServletRequest req, UtilityException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ RestControllerException.class })
	public ResponseEntity<?> handleRestControllerException(HttpServletRequest req, RestControllerException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ ExternalCallException.class })
	public ResponseEntity<?> handleExternalCallException(HttpServletRequest req, ExternalCallException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ BananazuraThrowable.class })
	public ResponseEntity<?> handleAnyThrowable(HttpServletRequest req, BananazuraThrowable e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
		Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

		String message = "RestController was called with unvalid request body. :: requestUrl={}";
		logger.error(message, req.getRequestURI(), e);
		String errorCode = AspectUtil.getErrorCodeWithoutVfError(appConf.getMethodArgumentNotValidErrorCode(),
				appConf.getDefaultErrorCode());
		if (specialLog != null)
			specialLog.logError(req, e);

		return ok(resultUtil.getInvalidArgumentResult(req, e, errorCode));
	}
}
