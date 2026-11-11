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
 * The gamemode calls {@link #recordPlayerSkin(UUID, TexturesProperty)} only when
 * it has a present skin, e.g. {@code getSkin(player).ifPresent(skin -> ...)}.
 * The method's null/blank/unsigned handling is a safety net, not an implicit
 * clear.
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
	 * <p>
	 * Never throws: a recorder failure is absorbed and the call returns a
	 * non-null inactive session whose status is {@link ReplayStatus#FAILED}
	 * (the no-op fallback reports {@link ReplayStatus#UNAVAILABLE}). Callers
	 * therefore need no try/catch and may always pass the result to
	 * {@link #stopRecording(ReplaySession)}.
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
	 * <p>
	 * The gamemode calls this only with a present skin, e.g.
	 * {@code getSkin(player).ifPresent(skin -> ...)}. The defensive checks are a
	 * safety net, not a call contract: a {@code null} player id, a {@code null}
	 * texture, a blank texture value or a blank (unsigned) signature is a
	 * <strong>no-op</strong> and never an implicit clear. Only a complete signed
	 * texture is recorded. Clearing remains a codec capability; an explicit
	 * {@code clearPlayerSkin} can be added later if a gamemode needs it.
	 *
	 * @param uuid the recorded player's UUID, or {@code null} to ignore
	 * @param skin the signed character texture, or {@code null}/blank/unsigned
	 *             to record nothing
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
