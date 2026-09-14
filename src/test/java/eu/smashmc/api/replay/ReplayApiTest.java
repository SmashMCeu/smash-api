package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReplayApiTest {

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
		assertThrows(UnsupportedOperationException.class, () -> config.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null).extra().isEmpty());
	}

	@Test
	void apiTypesUseNoBukkitTypes() {
		for (Class<?> type : new Class<?>[]{Replay.class, ReplayConfig.class, ReplaySession.class}) {
			for (Method method : type.getDeclaredMethods()) {
				assertNoBukkit(method.getParameterTypes());
				assertNoBukkit(new Class<?>[]{method.getReturnType()});
			}
			for (Constructor<?> constructor : type.getDeclaredConstructors()) {
				assertNoBukkit(constructor.getParameterTypes());
			}
		}
	}

	private static Replay newFallback() throws Exception {
		Constructor<FallbackReplay> constructor = FallbackReplay.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		return constructor.newInstance();
	}

	private static void assertNoBukkit(Class<?>[] types) {
		for (Class<?> type : types) {
			assertFalse(type.getName().startsWith("org.bukkit"), "unexpected Bukkit type " + type.getName());
		}
	}
}
