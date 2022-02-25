package io.github.berkayelken.bananazura.common.exception;

import static io.github.berkayelken.bananazura.common.util.ExceptionThrower.throwLocalizedThrowable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BananazuraThrowableTest {
	@Test
	public void testLocalizedThrowable() {
		Exception basicException = new Exception();
		BananazuraThrowable ex = throwLocalizedThrowable(Object.class, basicException, "errorCode");
		ex.setErrorCodeAnnotation(null);
		assertNull(ex.getErrorCodeAnnotation());
		assertEquals(Object.class, ex.getClass());
		assertEquals(basicException, ex.getCause());
		assertEquals("errorCode", ex.getErrorCode());
	}
}
