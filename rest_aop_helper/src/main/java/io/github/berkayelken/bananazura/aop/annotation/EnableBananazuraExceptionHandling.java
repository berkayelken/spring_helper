package io.github.berkayelken.bananazura.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.berkayelken.bananazura.aop.configuration.ExceptionHandlingConfiguration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ ExceptionHandlingConfiguration.class })
public @interface EnableBananazuraExceptionHandling {
}