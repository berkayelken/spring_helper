package io.github.berkayelken.bananazura.aop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.bananazura.aop.aspect.exception" })
public class ExceptionHandlingConfiguration {
}
