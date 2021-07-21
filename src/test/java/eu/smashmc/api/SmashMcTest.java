package eu.smashmc.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmashMcTest {

	interface Example {
	}

	@SmashApi
	interface ExampleApi {
	}

	@SmashApi({ Environment.BUKKIT, Environment.BUNGEECORD })
	interface DependentApi {
	}

	@BeforeEach
	public void setup() {
		SmashMc.clearApis();
	}

	@Test
	public void testRegisterApi() {
		ExampleApi api = mock(ExampleApi.class);
		SmashMc.registerApi(ExampleApi.class, api);
	}

	@Test
	public void testRegisterApiFail() {
		Example api = mock(Example.class);
		assertThrows(IllegalArgumentException.class, () -> SmashMc.registerApi(Example.class, api));
	}

	@Test
	public void testGetApi() {
		ExampleApi mock = mock(ExampleApi.class);
		SmashMc.registerApi(ExampleApi.class, mock);
		ExampleApi api = SmashMc.getApi(ExampleApi.class);
		assertEquals(mock, api);
	}

	@Test
	public void testGetApiFail() {
		assertThrows(IllegalStateException.class, () -> SmashMc.getApi(ExampleApi.class));
	}

	@Test
	public void testGetApiFailNoApi() {
		assertThrows(IllegalArgumentException.class, () -> SmashMc.getApi(Example.class));
	}

	@Test
	public void testDependentApi() {
		Environment.setEnvironment(Environment.BUKKIT);
		DependentApi mock = mock(DependentApi.class);
		SmashMc.registerApi(DependentApi.class, mock);
		DependentApi api = SmashMc.getApi(DependentApi.class);
		assertEquals(mock, api);
	}

	@Test
	public void testDependentApiAlternative() {
		Environment.setEnvironment(Environment.BUNGEECORD);
		DependentApi mock = mock(DependentApi.class);
		SmashMc.registerApi(DependentApi.class, mock);
		DependentApi api = SmashMc.getApi(DependentApi.class);
		assertEquals(mock, api);
	}

	@Test
	public void testDependentApiFail() {
		Environment.setEnvironment(Environment.NONE);
		DependentApi mock = mock(DependentApi.class);
		assertThrows(UnsupportedOperationException.class, () -> SmashMc.registerApi(DependentApi.class, mock));
	}

}
