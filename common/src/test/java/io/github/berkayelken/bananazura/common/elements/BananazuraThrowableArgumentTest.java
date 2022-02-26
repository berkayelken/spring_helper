package io.github.berkayelken.bananazura.common.elements;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(JUnit4.class)
public class BananazuraThrowableArgumentTest {
	@Test
	public void testArgument() {
		BananazuraThrowableArgument argument = new BananazuraThrowableArgument(Integer.MAX_VALUE, "integerField");
		assertNotNull(argument);
		assertEquals(Integer.class, argument.getArgumentType());
	}
}
