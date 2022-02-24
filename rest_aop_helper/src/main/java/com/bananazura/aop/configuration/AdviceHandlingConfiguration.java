package com.bananazura.aop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.bananazura.aop.advice" })
public class AdviceHandlingConfiguration {
}
