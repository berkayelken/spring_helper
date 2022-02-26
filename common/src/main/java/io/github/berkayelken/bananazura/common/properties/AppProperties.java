package io.github.berkayelken.bananazura.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Getter
@Setter
@NoArgsConstructor
public class AppProperties {
	@Value("${bananazura.spring.common.defaultErrorCode:#{null}}")
	private String defaultErrorCode;
	@Value("${bananazura.spring.common.utilityErrorCode:#{null}}")
	private String utilityErrorCode;
	@Value("${bananazura.spring.common.serviceErrorCode:#{null}}")
	private String serviceErrorCode;
	@Value("${bananazura.spring.common.modelErrorCode:#{null}}")
	private String modelErrorCode;
	@Value("${bananazura.spring.common.externalCallErrorCode:#{null}}")
	private String externalCallErrorCode;
	@Value("${bananazura.spring.common.restControllerErrorCode:#{null}}")
	private String restControllerErrorCode;
	@Value("${bananazura.spring.common.methodArgumentNotValidErrorCode:#{null}}")
	private String methodArgumentNotValidErrorCode;
}
