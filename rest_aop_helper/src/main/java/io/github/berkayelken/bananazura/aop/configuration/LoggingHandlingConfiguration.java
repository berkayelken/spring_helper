package io.github.berkayelken.bananazura.aop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.bananazura.aop.aspect.logging" })
public class LoggingHandlingConfiguration {
}
