package io.github.berkayelken.bananazura.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "bananazura.spring.common")
public class AppProperties {
	private String defaultErrorCode;
	private String utilityErrorCode;
	private String serviceErrorCode;
	private String modelErrorCode;
	private String externalCallErrorCode;
	private String restControllerErrorCode;
	private String methodArgumentNotValidErrorCode;
}
