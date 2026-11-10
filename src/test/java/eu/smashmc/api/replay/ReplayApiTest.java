package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.World;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class ReplayApiTest {

	private static final Class<?>[] API_TYPES = {
			Replay.class,
			ReplayConfig.class,
			ReplaySession.class,
			ReplayInfo.class,
			ReplayHandle.class,
			ReplayStatus.class,
			ReplayPlayer.class,
			ReplayReference.class,
			ReplayDirectory.class,
			ReplayPlayback.class,
			ReplayOpenResult.class,
			ReplayOpenResult.ReplayOpenStatus.class
	};

	private static final String[] FORBIDDEN_TYPE_PREFIXES = {
			"org.bukkit",
			"net.md_5.bungee",
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
	void replayDirectoryIsAnnotatedForBungeeWithFallback() {
		SmashComponent annotation = ReplayDirectory.class.getAnnotation(SmashComponent.class);
		assertNotNull(annotation);
		assertArrayEquals(new Environment[]{Environment.BUNGEECORD}, annotation.value());
		assertEquals(FallbackReplayDirectory.class, annotation.fallbackImpl());
	}

	@Test
	void replayPlaybackIsAnnotatedForBungeeWithFallback() {
		SmashComponent annotation = ReplayPlayback.class.getAnnotation(SmashComponent.class);
		assertNotNull(annotation);
		assertArrayEquals(new Environment[]{Environment.BUNGEECORD}, annotation.value());
		assertEquals(FallbackReplayPlayback.class, annotation.fallbackImpl());
	}

	@Test
	void fallbacksArePackagePrivateWithDefaultConstructors() throws Exception {
		assertPackagePrivateDefaultConstructor(FallbackReplay.class);
		assertPackagePrivateDefaultConstructor(FallbackReplayDirectory.class);
		assertPackagePrivateDefaultConstructor(FallbackReplayPlayback.class);
	}

	@Test
	void fallbackIsAlwaysIdleAndNeverThrows() throws Exception {
		Replay replay = newFallback(FallbackReplay.class);
		assertFalse(replay.isRecording());

		ReplaySession session = replay.startRecording(new ReplayConfig("smash", Map.of("k", "v")));
		assertNotNull(session);
		assertNotNull(session.id());
		assertEquals("", session.gamemodeId());
		assertNull(session.handle());
		assertEquals(ReplayStatus.UNAVAILABLE, session.status());
		assertNotNull(session.startedAt());
		assertFalse(replay.isRecording());

		replay.stopRecording(session);
		replay.stopRecording(null);
		assertNotNull(replay.startRecording(null));
		assertTrue(replay.info(session).isEmpty());
		assertTrue(replay.info(null).isEmpty());
	}

	@Test
	void fallbackAcceptsRecordedSkinsAndClearsAsANoOp() throws Exception {
		Replay replay = newFallback(FallbackReplay.class);

		assertDoesNotThrow(() -> replay.recordPlayerSkin(UUID.randomUUID(),
				new TexturesProperty("value", "signature")));
		assertDoesNotThrow(() -> replay.recordPlayerSkin(UUID.randomUUID(), null));
	}

	@Test
	void fallbackDirectoryIsAlwaysEmpty() throws Exception {
		ReplayDirectory directory = newFallback(FallbackReplayDirectory.class);

		assertTrue(directory.findByHandle(new ReplayHandle(7, "#7")).isEmpty());
		assertTrue(directory.findLive(UUID.randomUUID()).isEmpty());
		assertTrue(directory.findAt(UUID.randomUUID(), Instant.EPOCH).isEmpty());
		assertTrue(directory.findByHandle(null).isEmpty());
		assertTrue(directory.findLive(null).isEmpty());
		assertTrue(directory.findAt(null, null).isEmpty());
	}

	@Test
	void fallbackPlaybackIsAlwaysUnavailable() throws Exception {
		ReplayPlayback playback = newFallback(FallbackReplayPlayback.class);
		ProxiedPlayer staff = mock(ProxiedPlayer.class);

		CompletableFuture<ReplayOpenResult> future =
				playback.open(staff, new ReplayHandle(7, "#7"), 120);
		assertTrue(future.isDone());
		assertEquals(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, future.join().status());
		assertNull(future.join().reference());
		verifyNoInteractions(staff);

		assertEquals(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE,
				playback.open(null, null, 0).join().status());
	}

	@Test
	void infoDefaultsToEmptyForARecorderWithTheOriginalMethods() {
		Replay replay = new Replay() {
			@Override
			public ReplaySession startRecording(ReplayConfig config) {
				return new ReplaySession(UUID.randomUUID(), "");
			}

			@Override
			public void stopRecording(ReplaySession session) {
			}

			@Override
			public boolean isRecording() {
				return false;
			}

			@Override
			public void recordPlayerSkin(UUID uuid, TexturesProperty skin) {
			}
		};

		assertTrue(replay.info(new ReplaySession(UUID.randomUUID(), "smash")).isEmpty());
		assertTrue(replay.info(null).isEmpty());
	}

	@Test
	void configIsImmutableAndDefensive() {
		ReplayConfig config = new ReplayConfig("smash", Map.of("k", "v"));
		assertEquals("smash", config.gamemodeId());
		assertEquals("v", config.extra().get("k"));
		assertNull(config.world(), "the two-argument constructor delegates with a null capture world");
		assertNull(config.gameId(), "the two-argument constructor delegates with a null game id");
		assertThrows(UnsupportedOperationException.class, () -> config.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null).extra().isEmpty());
		assertNull(new ReplayConfig("smash", null).world());

		World world = mock(World.class);
		ReplayConfig scoped = new ReplayConfig("smash", Map.of("k", "v"), world);
		assertEquals("smash", scoped.gamemodeId());
		assertEquals("v", scoped.extra().get("k"));
		assertSame(world, scoped.world());
		assertNull(scoped.gameId(), "the three-argument constructor delegates with a null game id");
		assertThrows(UnsupportedOperationException.class, () -> scoped.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null, world).extra().isEmpty());

		ReplayConfig withGame = new ReplayConfig("smash", Map.of("k", "v"), world, "round-42");
		assertEquals("round-42", withGame.gameId());
		assertSame(world, withGame.world());
		assertEquals("v", withGame.extra().get("k"));
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
		assertNotNull(ReplayConfig.class.getConstructor(String.class, Map.class, World.class, String.class));
	}

	@Test
	void sessionKeepsTheSourceCompatibleTwoArgumentConstructor() {
		UUID id = UUID.randomUUID();
		ReplaySession session = new ReplaySession(id, "smash");

		assertEquals(id, session.id());
		assertEquals("smash", session.gamemodeId());
		assertNull(session.handle());
		assertEquals(ReplayStatus.RECORDING, session.status());
		assertNotNull(session.startedAt());
	}

	@Test
	void sessionExposesTheLiveHandleStatusAndStart() {
		ReplayHandle handle = new ReplayHandle(7, "#7");
		Instant startedAt = Instant.parse("2026-11-10T12:00:00Z");
		UUID id = UUID.randomUUID();

		ReplaySession session = new ReplaySession(id, "smash", handle, ReplayStatus.UPLOADING, startedAt);
		assertEquals(id, session.id());
		assertEquals("smash", session.gamemodeId());
		assertSame(handle, session.handle());
		assertEquals(ReplayStatus.UPLOADING, session.status());
		assertEquals(startedAt, session.startedAt());
	}

	@Test
	void replayInfoCarriesTheLiveSnapshot() {
		UUID id = UUID.randomUUID();
		ReplayHandle handle = new ReplayHandle(7, "#7");
		Instant startedAt = Instant.parse("2026-11-10T12:00:00Z");

		ReplayInfo info = new ReplayInfo(id, handle, "smash", startedAt, 320, ReplayStatus.READY);
		assertEquals(id, info.sessionId());
		assertSame(handle, info.handle());
		assertEquals("smash", info.gamemodeId());
		assertEquals(startedAt, info.startedAt());
		assertEquals(320, info.currentTick());
		assertEquals(ReplayStatus.READY, info.status());

		assertNull(new ReplayInfo(id, null, "smash", startedAt, 0, ReplayStatus.RECORDING).handle());
	}

	@Test
	void replayReferenceCarriesTheLookupData() {
		UUID sessionId = UUID.randomUUID();
		ReplayHandle handle = new ReplayHandle(7, "#7");
		UUID playerId = UUID.randomUUID();
		Instant startedAt = Instant.parse("2026-11-10T12:00:00Z");
		List<ReplayPlayer> roster = new ArrayList<>(List.of(new ReplayPlayer(playerId, "Steve")));

		ReplayReference reference = new ReplayReference(handle, sessionId, "Smash-1", "smash", "Mushroom",
				"round-42", roster, startedAt, 320, 400, ReplayStatus.READY);
		roster.add(new ReplayPlayer(UUID.randomUUID(), "Alex"));

		assertSame(handle, reference.handle());
		assertEquals(sessionId, reference.sessionId());
		assertEquals("Smash-1", reference.server());
		assertEquals("smash", reference.gamemodeId());
		assertEquals("Mushroom", reference.mapName());
		assertEquals("round-42", reference.gameId());
		assertEquals(List.of(new ReplayPlayer(playerId, "Steve")), reference.players());
		assertEquals(startedAt, reference.startedAt());
		assertEquals(320, reference.currentTick());
		assertEquals(400, reference.durationTicks());
		assertEquals(ReplayStatus.READY, reference.status());
		assertThrows(UnsupportedOperationException.class,
				() -> reference.players().add(new ReplayPlayer(UUID.randomUUID(), "Alex")));

		ReplayReference emptyRoster = new ReplayReference(handle, sessionId, "Smash-1", "smash", "Mushroom",
				null, null, startedAt, 0, 0, ReplayStatus.READY);
		assertTrue(emptyRoster.players().isEmpty());
	}

	@Test
	void statusesCoverTheDocumentedLifecycle() {
		assertArrayEquals(new ReplayStatus[]{
				ReplayStatus.RECORDING, ReplayStatus.UPLOADING, ReplayStatus.READY, ReplayStatus.FAILED,
				ReplayStatus.UNAVAILABLE
		}, ReplayStatus.values());
		assertArrayEquals(new ReplayOpenResult.ReplayOpenStatus[]{
				ReplayOpenResult.ReplayOpenStatus.ACCEPTED,
				ReplayOpenResult.ReplayOpenStatus.NO_REPLAY,
				ReplayOpenResult.ReplayOpenStatus.NOT_READY,
				ReplayOpenResult.ReplayOpenStatus.NO_PERMISSION,
				ReplayOpenResult.ReplayOpenStatus.NO_SERVER,
				ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE
		}, ReplayOpenResult.ReplayOpenStatus.values());
	}

	@Test
	void replayOpenResultAllowsAMissingReference() {
		ReplayReference reference = new ReplayReference(new ReplayHandle(7, "#7"), UUID.randomUUID(),
				"Smash-1", "smash", "Mushroom", null, List.of(), Instant.EPOCH, 0, 0, ReplayStatus.READY);

		ReplayOpenResult accepted = new ReplayOpenResult(
				ReplayOpenResult.ReplayOpenStatus.ACCEPTED, reference);
		assertEquals(ReplayOpenResult.ReplayOpenStatus.ACCEPTED, accepted.status());
		assertSame(reference, accepted.reference());

		ReplayOpenResult unavailable = new ReplayOpenResult(
				ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, null);
		assertEquals(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, unavailable.status());
		assertNull(unavailable.reference());
	}

	@Test
	void replayHandleIsTheDisplayForm() {
		ReplayHandle handle = new ReplayHandle(7, "#7");
		assertEquals(7, handle.id());
		assertEquals("#7", handle.display());
	}

	@Test
	void apiTypesUseNoCodecOrNmsTypesAndOnlyAllowedPlatformTypes() {
		for (Class<?> apiType : API_TYPES) {
			for (Method method : apiType.getDeclaredMethods()) {
				for (Type parameter : method.getGenericParameterTypes()) {
					assertAllowedApiType(apiType, parameter);
				}
				assertAllowedApiType(apiType, method.getGenericReturnType());
			}
			for (Constructor<?> constructor : apiType.getDeclaredConstructors()) {
				for (Type parameter : constructor.getGenericParameterTypes()) {
					assertAllowedApiType(apiType, parameter);
				}
			}
		}
	}

	@Test
	void allowedTypeGuardRecursesIntoGenericArguments() {
		assertThrows(AssertionError.class, () -> assertAllowedApiType(ReplaySession.class,
				NestedGenericTypes.class.getRecordComponents()[0].getGenericType()));
		assertThrows(AssertionError.class, () -> assertAllowedApiType(ReplaySession.class,
				NestedGenericTypes.class.getRecordComponents()[1].getGenericType()));
	}

	private record NestedGenericTypes(List<World> worlds, CompletableFuture<ProxiedPlayer> staff) {
	}

	private static void assertPackagePrivateDefaultConstructor(Class<?> type) throws Exception {
		assertFalse(Modifier.isPublic(type.getModifiers()), type.getName() + " must stay package-private");
		Constructor<?> constructor = type.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}

	private static <T> T newFallback(Class<T> type) throws Exception {
		Constructor<T> constructor = type.getDeclaredConstructor();
		constructor.setAccessible(true);
		return constructor.newInstance();
	}

	private static void assertAllowedApiType(Class<?> apiType, Type type) {
		if (type instanceof Class<?> clazz) {
			assertAllowedApiClass(apiType, clazz);
		} else if (type instanceof ParameterizedType parameterized) {
			assertAllowedApiType(apiType, parameterized.getRawType());
			for (Type argument : parameterized.getActualTypeArguments()) {
				assertAllowedApiType(apiType, argument);
			}
		} else if (type instanceof GenericArrayType array) {
			assertAllowedApiType(apiType, array.getGenericComponentType());
		} else if (type instanceof WildcardType wildcard) {
			for (Type bound : wildcard.getUpperBounds()) {
				assertAllowedApiType(apiType, bound);
			}
			for (Type bound : wildcard.getLowerBounds()) {
				assertAllowedApiType(apiType, bound);
			}
		} else if (type instanceof TypeVariable<?> variable) {
			for (Type bound : variable.getBounds()) {
				assertAllowedApiType(apiType, bound);
			}
		}
	}

	private static void assertAllowedApiClass(Class<?> apiType, Class<?> type) {
		while (type.isArray()) {
			type = type.getComponentType();
		}
		if (type == World.class && apiType == ReplayConfig.class) {
			return;
		}
		if (type == ProxiedPlayer.class && apiType == ReplayPlayback.class) {
			return;
		}
		for (String prefix : FORBIDDEN_TYPE_PREFIXES) {
			assertFalse(type.getName().startsWith(prefix),
					"unexpected " + prefix + " type " + type.getName() + " on " + apiType.getName());
		}
	}
}
