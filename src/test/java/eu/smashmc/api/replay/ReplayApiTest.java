package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import org.bukkit.World;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ReplayApiTest {

	private static final String[] FORBIDDEN_TYPE_PREFIXES = {
			"org.bukkit",
			"eu.smashmc.replay.codec",
			"net.minecraft"
	};

	@Test
	void replayIsAnnotatedForBukkitWithFallback() {
		SmashComponent annotation = Replay.class.getAnnotation(SmashComponent.class);
		assertNotNull(annotation);
		assertArrayEquals(new Environment[]{Environment.BUKKIT}, annotation.value());
		assertEquals(FallbackReplay.class, annotation.fallbackImpl());
	}

	@Test
	void fallbackHasDefaultConstructorAndImplementsReplay() throws Exception {
		assertTrue(Replay.class.isAssignableFrom(FallbackReplay.class));
		Constructor<FallbackReplay> constructor = FallbackReplay.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}

	@Test
	void fallbackIsAlwaysIdleAndNeverThrows() throws Exception {
		Replay replay = newFallback();
		assertFalse(replay.isRecording());

		ReplaySession session = replay.startRecording(new ReplayConfig("smash", Map.of("k", "v")));
		assertNotNull(session);
		assertNotNull(session.id());
		assertFalse(replay.isRecording());

		replay.stopRecording(session);
		replay.stopRecording(null);
		assertNotNull(replay.startRecording(null));
	}

	@Test
	void configIsImmutableAndDefensive() {
		ReplayConfig config = new ReplayConfig("smash", Map.of("k", "v"));
		assertEquals("smash", config.gamemodeId());
		assertEquals("v", config.extra().get("k"));
		assertNull(config.world(), "the two-argument constructor delegates with a null capture world");
		assertThrows(UnsupportedOperationException.class, () -> config.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null).extra().isEmpty());
		assertNull(new ReplayConfig("smash", null).world());

		World world = mock(World.class);
		ReplayConfig scoped = new ReplayConfig("smash", Map.of("k", "v"), world);
		assertEquals("smash", scoped.gamemodeId());
		assertEquals("v", scoped.extra().get("k"));
		assertSame(world, scoped.world());
		assertThrows(UnsupportedOperationException.class, () -> scoped.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null, world).extra().isEmpty());
	}

	@Test
	void configAllowsExactlyOneBukkitTypeForTheCaptureWorld() throws Exception {
		Class<?> worldType = null;
		for (RecordComponent component : ReplayConfig.class.getRecordComponents()) {
			if ("world".equals(component.getName())) {
				worldType = component.getType();
			}
		}
		assertEquals(World.class, worldType);
		assertEquals(World.class, ReplayConfig.class.getMethod("world").getReturnType());
		assertNotNull(ReplayConfig.class.getConstructor(String.class, Map.class, World.class));
	}

	@Test
	void apiTypesUseNoCodecOrNmsTypesAndOnlyWorldFromBukkit() {
		for (Class<?> type : new Class<?>[]{Replay.class, ReplayConfig.class, ReplaySession.class}) {
			for (Method method : type.getDeclaredMethods()) {
				for (Class<?> parameter : method.getParameterTypes()) {
					assertAllowedApiType(type, parameter);
				}
				assertAllowedApiType(type, method.getReturnType());
			}
			for (Constructor<?> constructor : type.getDeclaredConstructors()) {
				for (Class<?> parameter : constructor.getParameterTypes()) {
					assertAllowedApiType(type, parameter);
				}
			}
		}
	}

	private static Replay newFallback() throws Exception {
		Constructor<FallbackReplay> constructor = FallbackReplay.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		return constructor.newInstance();
	}

	private static void assertAllowedApiType(Class<?> owner, Class<?> type) {
		if (type == World.class && owner == ReplayConfig.class) {
			return;
		}
		for (String prefix : FORBIDDEN_TYPE_PREFIXES) {
			assertFalse(type.getName().startsWith(prefix),
					"unexpected " + prefix + " type " + type.getName() + " on " + owner.getName());
		}
	}
}
