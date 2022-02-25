package io.github.berkayelken.bananazura.common.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwServiceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ServiceExceptionTest {
	@Test
	public void testServiceException() {
		Exception basicException = new Exception();
		BananazuraThrowable ex = throwServiceException(Object.class, basicException, "errorCode");
		ex.setErrorCodeAnnotation(null);
		assertNull(ex.getErrorCodeAnnotation());
		assertEquals(Object.class, ex.getClass());
		assertEquals(basicException, ex.getCause());
		assertEquals("errorCode", ex.getErrorCode());
	}
}
