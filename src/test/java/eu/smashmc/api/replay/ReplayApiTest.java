package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReplayApiTest {

	private static final Class<?>[] API_TYPES = {
			Replay.class,
			ReplayGameIntegration.class,
			ReplayDirectory.class,
			ReplayPlayback.class,
			ReplayConfig.class,
			ReplaySession.class,
			ReplayInfo.class,
			ReplayHandle.class,
			ReplayStatus.class,
			ReplayPlayer.class,
			ReplayReference.class,
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
	void replayIsTheSingleComponentForBothEnvironments() {
		SmashComponent annotation = Replay.class.getAnnotation(SmashComponent.class);
		assertNotNull(annotation);
		assertArrayEquals(new Environment[]{Environment.BUKKIT, Environment.BUNGEECORD}, annotation.value());
		assertEquals(FallbackReplay.class, annotation.fallbackImpl());
	}

	@Test
	void subApisArePlainInterfacesObtainedFromTheComponent() throws Exception {
		for (Class<?> subApi : List.of(ReplayGameIntegration.class, ReplayDirectory.class, ReplayPlayback.class)) {
			assertTrue(subApi.isInterface(), subApi.getName() + " must stay an interface");
			assertNull(subApi.getAnnotation(SmashComponent.class),
					subApi.getName() + " must not be a separate @SmashComponent");
		}

		assertEquals(ReplayGameIntegration.class, Replay.class.getMethod("game").getReturnType());
		assertEquals(ReplayDirectory.class, Replay.class.getMethod("directory").getReturnType());
		assertEquals(ReplayPlayback.class, Replay.class.getMethod("playback").getReturnType());
	}

	@Test
	void fallbacksArePackagePrivateWithDefaultConstructors() throws Exception {
		assertPackagePrivateDefaultConstructor(FallbackReplay.class);
		assertPackagePrivateDefaultConstructor(FallbackReplayGameIntegration.class);
		assertPackagePrivateDefaultConstructor(FallbackReplayDirectory.class);
		assertPackagePrivateDefaultConstructor(FallbackReplayPlayback.class);
	}

	@Test
	void fallbackExposesTheThreeNoOpSubApis() throws Exception {
		Replay replay = newFallback(FallbackReplay.class);

		assertEquals(FallbackReplayGameIntegration.class, replay.game().getClass());
		assertEquals(FallbackReplayDirectory.class, replay.directory().getClass());
		assertEquals(FallbackReplayPlayback.class, replay.playback().getClass());
		assertSame(replay.game(), replay.game());
		assertSame(replay.directory(), replay.directory());
		assertSame(replay.playback(), replay.playback());
	}

	@Test
	void fallbackGameIntegrationIsAlwaysIdleAndNeverThrows() throws Exception {
		ReplayGameIntegration game = newFallback(FallbackReplay.class).game();
		assertFalse(game.isRecording());

		ReplaySession session = game.startRecording(new ReplayConfig("smash", Map.of("k", "v")));
		assertNotNull(session);
		assertNotNull(session.id());
		assertEquals("", session.gamemodeId());
		assertNull(session.handle());
		assertEquals(ReplayStatus.UNAVAILABLE, session.status());
		assertNotNull(session.startedAt());
		assertFalse(game.isRecording());

		game.stopRecording(session);
		game.stopRecording(null);
		assertNotNull(game.startRecording(null));
		assertTrue(game.info(session).isEmpty());
		assertTrue(game.info(null).isEmpty());
	}

	@Test
	void fallbackAcceptsRecordedSkinsAndNullAsANoOp() throws Exception {
		ReplayGameIntegration game = newFallback(FallbackReplay.class).game();

		assertDoesNotThrow(() -> game.recordPlayerSkin(UUID.randomUUID(),
				new TexturesProperty("value", "signature")));
		assertDoesNotThrow(() -> game.recordPlayerSkin(UUID.randomUUID(), (TexturesProperty) null));
	}

	@Test
	void centralSubApisAreNeverNull() throws Exception {
		Replay replay = newFallback(FallbackReplay.class);

		assertNotNull(replay.game());
		assertNotNull(replay.directory());
		assertNotNull(replay.playback());
	}

	@Test
	void skinRecordingContractTreatsAbsentOrIncompleteTexturesAsANoOp() {
		ContractSkinRecorder game = new ContractSkinRecorder();
		UUID id = UUID.randomUUID();
		TexturesProperty signed = new TexturesProperty("value", "signature");

		game.recordPlayerSkin(id, (TexturesProperty) null);
		game.recordPlayerSkin(null, signed);
		game.recordPlayerSkin(id, new TexturesProperty("", "signature"));
		game.recordPlayerSkin(id, new TexturesProperty("   ", "signature"));
		game.recordPlayerSkin(id, new TexturesProperty("value", ""));
		game.recordPlayerSkin(id, new TexturesProperty("value", "   "));
		assertTrue(game.recorded.isEmpty());

		game.recordPlayerSkin(id, signed);
		assertEquals(List.of(signed), game.recorded);
	}

	@Test
	void fallbackDirectoryCompletesEveryLookupEmpty() throws Exception {
		ReplayDirectory directory = newFallback(FallbackReplay.class).directory();

		assertEmpty(directory.findByHandle(new ReplayHandle(7, "#7")));
		assertEmpty(directory.findAt(UUID.randomUUID(), Instant.EPOCH));
		assertEmpty(directory.findByHandle(null));
		assertEmpty(directory.findAt(null, null));
	}

	@Test
	void fallbackDirectoryCannotArchive() throws Exception {
		ReplayDirectory directory = newFallback(FallbackReplay.class).directory();
		UUID actor = UUID.randomUUID();

		assertArchiveFails(directory.archive(new ReplayHandle(7, "#7"), actor, "punishment proof"));
		assertArchiveFails(directory.archive(new ReplayHandle(7, "#7"), actor, null));
		assertArchiveFails(directory.archive(null, null, null));
	}

	@Test
	void fallbackPlaybackIsAlwaysUnavailable() throws Exception {
		ReplayPlayback playback = newFallback(FallbackReplay.class).playback();
		UUID viewer = UUID.randomUUID();

		CompletableFuture<ReplayOpenResult> future =
				playback.open(viewer, new ReplayHandle(7, "#7"), 120);
		assertTrue(future.isDone());
		assertEquals(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, future.join().status());
		assertNull(future.join().reference());

		assertEquals(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE,
				playback.open(null, null, 0).join().status());
	}

	@Test
	void directoryLookupsAreAsynchronousOptionalReferences() {
		assertEquals(3, ReplayDirectory.class.getDeclaredMethods().length);
		for (Method method : ReplayDirectory.class.getDeclaredMethods()) {
			if (method.getName().equals("archive")) {
				continue;
			}
			assertEquals(CompletableFuture.class, method.getReturnType(), method.getName());

			ParameterizedType future = (ParameterizedType) method.getGenericReturnType();
			assertEquals(CompletableFuture.class, future.getRawType(), method.getName());
			ParameterizedType optional = (ParameterizedType) future.getActualTypeArguments()[0];
			assertEquals(Optional.class, optional.getRawType(), method.getName());
			assertEquals(ReplayReference.class, optional.getActualTypeArguments()[0], method.getName());
		}
	}

	@Test
	void directoryArchiveTakesTheHandleActorAndAnOptionalReason() throws Exception {
		Method archive = ReplayDirectory.class.getMethod("archive", ReplayHandle.class, UUID.class, String.class);

		ParameterizedType future = (ParameterizedType) archive.getGenericReturnType();
		assertEquals(CompletableFuture.class, future.getRawType());
		assertArrayEquals(new Type[]{Void.class}, future.getActualTypeArguments());
	}

	@Test
	void infoDefaultsToEmptyForARecorderWithTheOriginalMethods() {
		ReplayGameIntegration game = new ReplayGameIntegration() {
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

		assertTrue(game.info(new ReplaySession(UUID.randomUUID(), "smash")).isEmpty());
		assertTrue(game.info(null).isEmpty());
	}

	@Test
	void configIsImmutableAndDefensive() {
		ReplayConfig config = new ReplayConfig("smash", Map.of("k", "v"));
		assertEquals("smash", config.gamemodeId());
		assertEquals("v", config.extra().get("k"));
		assertNull(config.worldName(), "the two-argument constructor delegates with a null capture world");
		assertNull(config.gameId(), "the two-argument constructor delegates with a null game id");
		assertThrows(UnsupportedOperationException.class, () -> config.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null).extra().isEmpty());
		assertNull(new ReplayConfig("smash", null).worldName());

		ReplayConfig scoped = new ReplayConfig("smash", Map.of("k", "v"), "world_nether");
		assertEquals("smash", scoped.gamemodeId());
		assertEquals("v", scoped.extra().get("k"));
		assertEquals("world_nether", scoped.worldName());
		assertNull(scoped.gameId(), "the three-argument constructor delegates with a null game id");
		assertThrows(UnsupportedOperationException.class, () -> scoped.extra().put("x", "y"));
		assertTrue(new ReplayConfig("smash", null, "world_nether").extra().isEmpty());

		ReplayConfig withGame = new ReplayConfig("smash", Map.of("k", "v"), "world_nether", "round-42");
		assertEquals("round-42", withGame.gameId());
		assertEquals("world_nether", withGame.worldName());
		assertEquals("v", withGame.extra().get("k"));
	}

	@Test
	void configScopesCaptureByWorldNameOnly() throws Exception {
		Class<?> worldType = null;
		for (RecordComponent component : ReplayConfig.class.getRecordComponents()) {
			if ("worldName".equals(component.getName())) {
				worldType = component.getType();
			}
		}
		assertEquals(String.class, worldType);
		assertEquals(String.class, ReplayConfig.class.getMethod("worldName").getReturnType());
		assertNotNull(ReplayConfig.class.getConstructor(String.class, Map.class, String.class));
		assertNotNull(ReplayConfig.class.getConstructor(String.class, Map.class, String.class, String.class));
		assertArrayEquals(new String[]{"gamemodeId", "extra", "worldName", "gameId"},
				componentNames(ReplayConfig.class));
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
				"round-42", roster, startedAt, 400, ReplayStatus.READY);
		roster.add(new ReplayPlayer(UUID.randomUUID(), "Alex"));

		assertSame(handle, reference.handle());
		assertEquals(sessionId, reference.sessionId());
		assertEquals("Smash-1", reference.server());
		assertEquals("smash", reference.gamemodeId());
		assertEquals("Mushroom", reference.mapName());
		assertEquals("round-42", reference.gameId());
		assertEquals(List.of(new ReplayPlayer(playerId, "Steve")), reference.players());
		assertEquals(startedAt, reference.startedAt());
		assertEquals(400, reference.durationTicks());
		assertEquals(ReplayStatus.READY, reference.status());
		assertThrows(UnsupportedOperationException.class,
				() -> reference.players().add(new ReplayPlayer(UUID.randomUUID(), "Alex")));

		ReplayReference emptyRoster = new ReplayReference(handle, sessionId, "Smash-1", "smash", "Mushroom",
				null, null, startedAt, 0, ReplayStatus.READY);
		assertTrue(emptyRoster.players().isEmpty());
	}

	@Test
	void replayReferenceDeclaresTheDocumentedComponentsInOrder() {
		assertArrayEquals(new String[]{"handle", "sessionId", "server", "gamemodeId", "mapName", "gameId",
				"players", "startedAt", "durationTicks", "status"}, componentNames(ReplayReference.class));
		assertArrayEquals(new Class<?>[]{ReplayHandle.class, UUID.class, String.class, String.class, String.class,
				String.class, List.class, Instant.class, int.class, ReplayStatus.class},
				componentTypes(ReplayReference.class));
	}

	@Test
	void replayReferenceAllowsAnUnpersistedSessionId() {
		ReplayHandle handle = new ReplayHandle(7, "#7");
		Instant startedAt = Instant.parse("2026-11-10T12:00:00Z");

		ReplayReference reference = new ReplayReference(handle, null, "Smash-1", "smash", "Mushroom",
				null, List.of(), startedAt, 400, ReplayStatus.READY);

		assertNull(reference.sessionId(), "a backend that does not persist the session id passes null");
		assertSame(handle, reference.handle());
		assertEquals("Smash-1", reference.server());
		assertEquals(ReplayStatus.READY, reference.status());
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
				"Smash-1", "smash", "Mushroom", null, List.of(), Instant.EPOCH, 0, ReplayStatus.READY);

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
	void apiTypesUseNoCodecPlatformOrNmsTypes() {
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
		Type players = NestedGenericTypes.class.getRecordComponents()[0].getGenericType();
		Type nestedPlayers = NestedGenericTypes.class.getRecordComponents()[1].getGenericType();
		String[] argumentOnly = {"eu.smashmc.api.replay.ReplayPlayer"};

		assertThrows(AssertionError.class, () -> assertAllowedApiType(ReplaySession.class, players, argumentOnly));
		assertThrows(AssertionError.class,
				() -> assertAllowedApiType(ReplaySession.class, nestedPlayers, argumentOnly));
	}

	/**
	 * Reference implementation of the documented defensive skin contract: only a
	 * complete signed texture is recorded, absent/incomplete values are no-ops
	 * and never clears. Stands in for a gamemode/recorder, not the plugin.
	 */
	private static final class ContractSkinRecorder implements ReplayGameIntegration {

		private final List<TexturesProperty> recorded = new ArrayList<>();

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
			if (uuid == null || skin == null || blank(skin.getValue()) || blank(skin.getSignature())) {
				return;
			}
			recorded.add(skin);
		}

		private static boolean blank(String value) {
			return value == null || value.isBlank();
		}
	}

	private record NestedGenericTypes(List<ReplayPlayer> players,
									  CompletableFuture<List<ReplayPlayer>> nestedPlayers) {
	}

	private static void assertEmpty(CompletableFuture<Optional<ReplayReference>> lookup) {
		assertTrue(lookup.isDone());
		assertTrue(lookup.join().isEmpty());
	}

	private static void assertArchiveFails(CompletableFuture<Void> archive) {
		assertTrue(archive.isDone());
		assertTrue(archive.isCompletedExceptionally(), "a fallback must not report a successful archive");
		CompletionException failure = assertThrows(CompletionException.class, archive::join);
		assertNotNull(failure.getCause());
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
		assertAllowedApiType(apiType, type, FORBIDDEN_TYPE_PREFIXES);
	}

	private static void assertAllowedApiType(Class<?> apiType, Type type, String[] forbiddenPrefixes) {
		if (type instanceof Class<?> clazz) {
			assertAllowedApiClass(apiType, clazz, forbiddenPrefixes);
		} else if (type instanceof ParameterizedType parameterized) {
			assertAllowedApiType(apiType, parameterized.getRawType(), forbiddenPrefixes);
			for (Type argument : parameterized.getActualTypeArguments()) {
				assertAllowedApiType(apiType, argument, forbiddenPrefixes);
			}
		} else if (type instanceof GenericArrayType array) {
			assertAllowedApiType(apiType, array.getGenericComponentType(), forbiddenPrefixes);
		} else if (type instanceof WildcardType wildcard) {
			for (Type bound : wildcard.getUpperBounds()) {
				assertAllowedApiType(apiType, bound, forbiddenPrefixes);
			}
			for (Type bound : wildcard.getLowerBounds()) {
				assertAllowedApiType(apiType, bound, forbiddenPrefixes);
			}
		} else if (type instanceof TypeVariable<?> variable) {
			for (Type bound : variable.getBounds()) {
				assertAllowedApiType(apiType, bound, forbiddenPrefixes);
			}
		}
	}

	private static void assertAllowedApiClass(Class<?> apiType, Class<?> type, String[] forbiddenPrefixes) {
		while (type.isArray()) {
			type = type.getComponentType();
		}
		for (String prefix : forbiddenPrefixes) {
			assertFalse(type.getName().startsWith(prefix),
					"unexpected " + prefix + " type " + type.getName() + " on " + apiType.getName());
		}
	}

	private static String[] componentNames(Class<?> type) {
		return Stream.of(type.getRecordComponents()).map(RecordComponent::getName).toArray(String[]::new);
	}

	private static Class<?>[] componentTypes(Class<?> type) {
		return Stream.of(type.getRecordComponents()).map(RecordComponent::getType).toArray(Class<?>[]::new);
	}
}
