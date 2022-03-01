package io.github.berkayelken.bananazura.aop.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class HttpStatusUtil {
	private static HttpStatusUtil instance;

	@Value("${bananazura.spring.errorCodes.default}")
	private int defaultStatus;

	@Value("${bananazura.spring.errorCodes.service}")
	private int serviceStatus;

	@Value("${bananazura.spring.errorCodes.model}")
	private int modelStatus;

	@Value("${bananazura.spring.errorCodes.utility}")
	private int utilityStatus;

	@Value("${bananazura.spring.errorCodes.restcontroller}")
	private int restControllerStatus;

	@Value("${bananazura.spring.errorCodes.extcall}")
	private int externalCallStatus;

	@Value("${bananazura.spring.errorCodes.argumentNotValid}")
	private int argumentNotValidStatus;

	@Value("${bananazura.spring.errorCodes.other}")
	private int otherStatus;

	@PostConstruct
	private void init() {
		instance = this;
	}

	static HttpStatus getServiceExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.serviceStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	static HttpStatus getModelExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.modelStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	static HttpStatus getUtilityExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.utilityStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	static HttpStatus getRestControllerExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.restControllerStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	static HttpStatus getExternalCallExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.externalCallStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	static HttpStatus getArgumentNotValidExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.argumentNotValidStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.BAD_REQUEST;
		}

		return httpStatus;
	}

	static HttpStatus getOtherExceptionStatus() {
		HttpStatus httpStatus = getHttpStatus(instance.otherStatus, instance.defaultStatus);

		if(httpStatus == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return httpStatus;
	}

	private static HttpStatus getHttpStatus(int status, int defaultCode) {
		if(status == 0) {
			return HttpStatus.resolve(defaultCode);
		}

		HttpStatus httpStatus = HttpStatus.resolve(status);

		if(httpStatus == null) {
			httpStatus = HttpStatus.resolve(defaultCode);
		}

		return httpStatus;
	}
}
