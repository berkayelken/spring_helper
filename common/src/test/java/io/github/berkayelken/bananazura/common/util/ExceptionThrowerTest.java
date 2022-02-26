package io.github.berkayelken.bananazura.common.util;

import io.github.berkayelken.bananazura.common.elements.BananazuraThrowableArgument;
import io.github.berkayelken.bananazura.common.exception.BananazuraThrowable;
import io.github.berkayelken.bananazura.common.exception.ExternalCallException;
import io.github.berkayelken.bananazura.common.exception.ModelException;
import io.github.berkayelken.bananazura.common.exception.RestControllerException;
import io.github.berkayelken.bananazura.common.exception.ServiceException;
import io.github.berkayelken.bananazura.common.exception.UtilityException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwBananazuraThrowable;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(JUnit4.class)
public class ExceptionThrowerTest {
	@Test
	public void testThrowBananazuraThrowable() {
		Throwable cause = new NullPointerException();
		BananazuraThrowable  bananazuraThrowable = throwBananazuraThrowable(BananazuraThrowable.class, getClass(),
				cause, "errorCode");
		assertNotNull(bananazuraThrowable);
		bananazuraThrowable.setErrorCodeAnnotation(null);
		assertNull(bananazuraThrowable.getErrorCodeAnnotation());
		assertEquals(getClass(), bananazuraThrowable.getThrowerClass());
		assertNotNull(bananazuraThrowable.getMessage());
		assertNotNull(bananazuraThrowable.getLocalizedMessage());
		assertEquals("errorCode", bananazuraThrowable.getErrorCode());

		assertEquals(ExternalCallException.class, throwBananazuraThrowable(ExternalCallException.class, getClass(),
				cause, "errorCode").getClass());

		assertEquals(ModelException.class, throwBananazuraThrowable(ModelException.class, getClass(),
				cause, "errorCode").getClass());

		assertEquals(RestControllerException.class, throwBananazuraThrowable(RestControllerException.class, getClass(),
				cause, "errorCode").getClass());

		assertEquals(ServiceException.class, throwBananazuraThrowable(ServiceException.class, getClass(),
				cause, "errorCode").getClass());

		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode").getClass());
	}

	@Test
	public void testThrowBananazuraThrowableWithArguments() {
		Throwable cause = new NullPointerException();
		List<String> logFields = ExceptionThrower.createLogFieldList("field1", "field2");
		List<BananazuraThrowableArgument<?>> arguments = ExceptionThrower.createArguments(logFields,
				Integer.MIN_VALUE, Boolean.FALSE);
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", arguments).getClass());
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", ExceptionThrower.createArguments(null, Integer.MIN_VALUE, Boolean.FALSE)).getClass());
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", ExceptionThrower.createArguments(logFields)).getClass());
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", ExceptionThrower.createArguments(new ArrayList<>(),
						Integer.MIN_VALUE, Boolean.FALSE)).getClass());
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", ExceptionThrower.createArguments(logFields, new Object[]{})).getClass());
		assertEquals(UtilityException.class, throwBananazuraThrowable(UtilityException.class, getClass(),
				cause, "errorCode", ExceptionThrower.createArguments(logFields, Integer.MIN_VALUE)).getClass());
	}
}
