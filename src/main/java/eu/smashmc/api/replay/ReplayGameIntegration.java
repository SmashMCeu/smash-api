package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Synchronous recording API, obtained from {@link Replay#game()} and called from
 * the server main thread.
 */
public interface ReplayGameIntegration {

	/**
	 * Starts capture; at most one recording is active. Never throws: a failure
	 * returns a non-null session with status {@link ReplayStatus#FAILED}, or
	 * {@link ReplayStatus#UNAVAILABLE} when no recording implementation is
	 * available.
	 *
	 * @param config the recording configuration
	 * @return a non-null session to pass to {@link #stopRecording(ReplaySession)}
	 */
	ReplaySession startRecording(ReplayConfig config);

	/**
	 * Stops the recording and uploads it in the background. A {@code null} or
	 * unknown session is ignored.
	 *
	 * @param session the session to stop; {@code null} is ignored
	 */
	void stopRecording(ReplaySession session);

	/**
	 * @return {@code true} while a recording is active
	 */
	boolean isRecording();

	/**
	 * Records a player's character skin. A {@code null} player id, a {@code null}
	 * texture, a blank value or a blank (unsigned) signature is ignored and never
	 * clears a previously recorded skin.
	 *
	 * @param uuid the player's UUID, or {@code null} to ignore
	 * @param skin the signed texture, or {@code null}/blank/unsigned to ignore
	 */
	void recordPlayerSkin(UUID uuid, @Nullable TexturesProperty skin);

	/**
	 * Returns a snapshot of a session, or empty when none is available. Empty does
	 * not mean that recording is inactive; use {@link #isRecording()}.
	 *
	 * @param session the session to inspect, or {@code null} for an empty result
	 * @return the snapshot, or empty when unavailable
	 */
	default Optional<ReplayInfo> info(@Nullable ReplaySession session) {
		return Optional.empty();
	}
}
