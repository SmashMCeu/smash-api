package eu.smashmc.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class SmashMcTest {

	@Test
	public void testRegisterApi() {
		SmashApi api = mock(SmashApi.class);
		SmashMc.registerApi(SmashApi.class, api);
	}

	@Test
	public void testGetApi() {
		SmashApi mock = mock(SmashApi.class);
		SmashMc.registerApi(SmashApi.class, mock);
		SmashApi api = SmashMc.getApi(SmashApi.class);
		assertEquals(mock, api);
	}

	@Test
	public void testGetApiFail() {
		assertThrows(UnsupportedOperationException.class, () -> SmashMc.getApi(SmashApi.class));
	}
}
