package io.github.berkayelken.bananazura.common.exception;

/**
 * @author 		: Berkay Yelken (https://github.com/berkayelken)
 * Since 	:  1.0.0
 * Project		: Bananazura Common (https://github.com/berkayelken/spring_helper/tree/master/common)
 */
public class ModelException extends BananazuraThrowable {
	private static final long serialVersionUID = 4180370731701806842L;

	ModelException(BananazuraExceptionBuilder<? extends BananazuraThrowable> builder) {
		super(builder);
	}
}