package io.github.berkayelken.bananazura.common.elements;

import lombok.Getter;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Date 		: 25-02-2022
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
@Getter
public class BananazuraThrowableArgument<T> {
	private T argument;
	private String logField;

	public BananazuraThrowableArgument(T argument, String logField) {
		this.argument = argument;
		this.logField = logField;
	}

	public Class<T> getArgumentType() {
		return (Class<T>) argument.getClass();
	}
}
