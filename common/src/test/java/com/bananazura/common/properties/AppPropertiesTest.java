package com.bananazura.common.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AppPropertiesTest {
	@Test
	public void testAppProperties() {
		AppProperties prop = new AppProperties();

		prop.setDefaultErrorCode("default");
		prop.setExternalCallErrorCode("external");
		prop.setMethodArgumentNotValidErrorCode("argument");
		prop.setModelErrorCode("model");
		prop.setRestControllerErrorCode("rest");
		prop.setServiceErrorCode("service");
		prop.setUtilityErrorCode("utility");

		assertEquals("default", prop.getDefaultErrorCode());
		assertEquals("external", prop.getExternalCallErrorCode());
		assertEquals("argument", prop.getMethodArgumentNotValidErrorCode());
		assertEquals("model", prop.getModelErrorCode());
		assertEquals("rest", prop.getRestControllerErrorCode());
		assertEquals("service", prop.getServiceErrorCode());
		assertEquals("utility", prop.getUtilityErrorCode());
	}
}
