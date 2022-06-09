package io.github.berkayelken.bananazura.aop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura AOP (https://github.com/berkayelken/spring_helper/tree/master/rest_aop_helper)
 */
@Configuration
@ComponentScan({ "io.github.berkayelken.bananazura.aop.advice" })
public class AdviceHandlingConfiguration {
}
