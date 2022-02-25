package io.github.berkayelken.bananazura.common.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.github.berkayelken.bananazura.common.util.ExceptionThrower;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModelExceptionTest {
	@Test
	public void testModelException() {
		Exception basicException = new Exception();
		BananazuraThrowable ex = ExceptionThrower.throwModelException(Object.class, basicException, "errorCode");
		ex.setErrorCodeAnnotation(null);
		assertNull(ex.getErrorCodeAnnotation());
		assertEquals(Object.class, ex.getClass());
		assertEquals(basicException, ex.getCause());
		assertEquals("errorCode", ex.getErrorCode());
	}
}
