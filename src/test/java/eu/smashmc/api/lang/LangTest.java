package eu.smashmc.api.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LangTest {
	@Test
	public void testGetCallingClass() {
		Class<?> caller = Lang.getCallingClass();
		assertEquals(LangTest.class, caller);
	}
}
