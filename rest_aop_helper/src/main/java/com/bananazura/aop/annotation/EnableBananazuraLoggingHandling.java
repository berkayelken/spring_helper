package com.bananazura.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bananazura.aop.configuration.ExceptionHandlingConfiguration;

import com.bananazura.aop.configuration.LoggingHandlingConfiguration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ LoggingHandlingConfiguration.class })
public @interface EnableBananazuraLoggingHandling {
}
