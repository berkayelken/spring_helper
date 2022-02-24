package com.bananazura.common.exception;

import static com.bananazura.common.util.ExceptionThrower.throwExternalCallException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ExternalCallExceptionTest {
	@Test
	public void testExternallCall() {
		Exception basicException = new Exception();
		BananazuraThrowable ex = throwExternalCallException(Object.class, basicException, "errorCode");
		ex.setErrorCodeAnnotation(null);
		assertNull(ex.getErrorCodeAnnotation());
		assertEquals(Object.class, ex.getClass());
		assertEquals(basicException, ex.getCause());
		assertEquals("errorCode", ex.getErrorCode());
	}
}
