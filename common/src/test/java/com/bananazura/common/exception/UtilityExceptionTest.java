package com.bananazura.common.exception;

import static com.bananazura.common.util.ExceptionThrower.throwUtilityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UtilityExceptionTest {
	@Test
	public void testUtilityException() {
		Exception basicException = new Exception();
		BananazuraThrowable ex = throwUtilityException(Object.class, basicException, "errorCode");
		ex.setErrorCodeAnnotation(null);
		assertNull(ex.getErrorCodeAnnotation());
		assertEquals(Object.class, ex.getClass());
		assertEquals(basicException, ex.getCause());
		assertEquals("errorCode", ex.getErrorCode());
	}
}
