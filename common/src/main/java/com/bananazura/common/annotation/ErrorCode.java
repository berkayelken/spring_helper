package com.bananazura.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

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
