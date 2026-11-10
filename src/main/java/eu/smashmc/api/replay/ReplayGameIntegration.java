package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Local, synchronous recorder/gamemode integration of the replay domain,
 * obtained from {@link Replay#game()}. It is the <strong>recorder</strong>
 * surface: start/stop plus "is one running", the canonical character skin of a
 * recorded player, and a live snapshot of the active session.
 * <p>
 * The recorder keeps this state on the server main thread, so the methods are
 * synchronous; remote queries belong to {@link ReplayDirectory} and
 * {@link ReplayPlayback}. No protocol, event ID, snapshot, framing or codec
 * type crosses this boundary (ADR-0007/0009).
 */
public interface ReplayGameIntegration {

	/**
	 * Begin capture for a game. Called once per game from the server main thread
	 * at game start.
	 *
	 * @param config the gamemode id, game id and reserved header data for this
	 *               recording
	 * @return a non-null opaque handle used to stop this recording; at most one
	 *         recording is active per server
	 */
	ReplaySession startRecording(ReplayConfig config);

	/**
	 * Finalize the recording identified by the given handle and upload it
	 * asynchronously. Called at game end. Safe to call for a {@code null} or
	 * unknown handle, which is ignored.
	 *
	 * @param session the handle returned by {@link #startRecording(ReplayConfig)}
	 */
	void stopRecording(ReplaySession session);

	/**
	 * @return {@code true} while a recording is active
	 */
	boolean isRecording();

	/**
	 * Record the canonical character skin the gamemode applied to a player,
	 * called whenever that skin is applied (including a mid-game character
	 * change). Playback reproduces the changes from these values instead of
	 * resolving the player's live identity skin (ADR-0039).
	 *
	 * @param uuid the recorded player's UUID
	 * @param skin the signed character texture, or {@code null} to clear it so
	 *             the player falls back to its default/live skin
	 */
	void recordPlayerSkin(UUID uuid, @Nullable TexturesProperty skin);

	/**
	 * Read a live snapshot of a recording session, including the backend handle
	 * once the recorder has published it and the tick it has reached.
	 * <p>
	 * The default implementation returns an empty optional: a recorder that does
	 * not track live handles needs no change. The no-op fallback is empty too, so
	 * callers must treat absence as "no information", never as "not recording".
	 *
	 * @param session the session returned by
	 *                {@link #startRecording(ReplayConfig)}, or {@code null}
	 * @return the snapshot, or empty when the recorder cannot provide one
	 */
	default Optional<ReplayInfo> info(@Nullable ReplaySession session) {
		return Optional.empty();
	}
}
