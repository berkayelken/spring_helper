package io.github.berkayelken.bananazura.aop.advice;

import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getArgumentNotValidExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getExternalCallExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getModelExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getOtherExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getRestControllerExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getServiceExceptionStatus;
import static io.github.berkayelken.bananazura.aop.advice.HttpStatusUtil.getUtilityExceptionStatus;
import static io.github.berkayelken.bananazura.common.util.ErrorCodeUtil.getErrorCodeWithoutVfError;
import static org.springframework.http.ResponseEntity.status;

import javax.servlet.http.HttpServletRequest;

import io.github.berkayelken.bananazura.common.exception.RepositoryException;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 	: Berkay Yelken (https://github.com/berkayelken)
 *Since 	:  1.0.0
 * Project 	: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice:true}' == 'true'")
public class RestExceptionHandler {
	@Autowired
	private AppProperties appConf;

	@Autowired
	private FailResult<?> resultUtil;

	@Autowired(required = false)
	private SpecialLog specialLog;

	private static final HttpStatus SERVICE_STATUS = getServiceExceptionStatus();
	private static final HttpStatus REPOSITORY_STATUS = getServiceExceptionStatus();
	private static final HttpStatus MODEL_STATUS = getModelExceptionStatus();
	private static final HttpStatus UTILITY_STATUS = getUtilityExceptionStatus();
	private static final HttpStatus REST_CONTROLLER_STATUS = getRestControllerExceptionStatus();
	private static final HttpStatus EXT_CALL_STATUS = getExternalCallExceptionStatus();
	private static final HttpStatus BANANAZURA_STATUS = getOtherExceptionStatus();
	private static final HttpStatus ARGUMENT_NOT_VALID_STATUS = getArgumentNotValidExceptionStatus();

	@ExceptionHandler({ ServiceException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.service:true}' == 'true'")
	public ResponseEntity<?> handleServiceException(HttpServletRequest req, ServiceException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(SERVICE_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ RepositoryException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.repository:true}' == 'true'")
	public ResponseEntity<?> handleRespositoryException(HttpServletRequest req, RepositoryException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(REPOSITORY_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ ModelException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.model:true}' == 'true'")
	public ResponseEntity<?> handleModelException(HttpServletRequest req, ModelException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(MODEL_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ UtilityException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.utility:true}' == 'true'")
	public ResponseEntity<?> handleUtilityException(HttpServletRequest req, UtilityException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(UTILITY_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ RestControllerException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.restcontroller:true}' == 'true'")
	public ResponseEntity<?> handleRestControllerException(HttpServletRequest req, RestControllerException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(REST_CONTROLLER_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ ExternalCallException.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.extcall:true}' == 'true'")
	public ResponseEntity<?> handleExternalCallException(HttpServletRequest req, ExternalCallException e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(EXT_CALL_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler({ BananazuraThrowable.class })
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.other:true}' == 'true'")
	public ResponseEntity<?> handleAnyThrowable(HttpServletRequest req, BananazuraThrowable e) {
		Logger logger = LoggerFactory.getLogger(e.getThrowerClass());
		logger.error(e.getMessage(), e.getCause());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(BANANAZURA_STATUS).body(resultUtil.getFailResult(req, e));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ConditionalOnExpression("'${bananazura.spring.aop.rest.advice.argument:true}' == 'true'")
	public ResponseEntity<?> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
		Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

		String message = "RestController was called with unvalid request body. :: requestUrl={}";
		logger.error(message, req.getRequestURI(), e);
		String errorCode = getErrorCodeWithoutVfError(appConf.getMethodArgumentNotValidErrorCode(),
				appConf.getDefaultErrorCode());
		if (specialLog != null)
			specialLog.logError(req, e);

		return status(ARGUMENT_NOT_VALID_STATUS).body(resultUtil.getInvalidArgumentResult(req, e, errorCode));
	}
}
