package io.github.berkayelken.bananazura.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ErrorCode {
	@AliasFor("code")
	String value() default "";

	@AliasFor("value")
	String code() default "";

	String[] placeholder() default {};

	int[] placeholderIndexer() default {};

	boolean replacePlaceholder() default false;

	String[] errorCodes() default {};

	Class<? extends Throwable>[] errors() default {};

	String defaultError() default "";
}
