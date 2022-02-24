package com.bananazura.aop.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.bananazura.aop.advice" })
@EnableConfigurationProperties
public class AdviceHandlingConfiguration {
}
