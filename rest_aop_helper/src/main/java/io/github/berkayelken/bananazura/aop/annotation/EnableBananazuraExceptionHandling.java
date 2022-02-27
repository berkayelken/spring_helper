package io.github.berkayelken.bananazura.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.berkayelken.bananazura.aop.configuration.ExceptionHandlingConfiguration;

import org.springframework.context.annotation.Import;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ ExceptionHandlingConfiguration.class })
public @interface EnableBananazuraExceptionHandling {
}
