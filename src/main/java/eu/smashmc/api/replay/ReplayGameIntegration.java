package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Records games and supplies the character skins applied during a recording,
 * obtained from {@link Replay#game()}.
 * <p>
 * All methods are synchronous and are expected to be called from the server
 * main thread.
 */
public interface ReplayGameIntegration {

	/**
	 * Starts capture for a game. At most one recording is active at a time.
	 * <p>
	 * Never throws: if capture cannot start, a non-null inactive session is
	 * returned with status {@link ReplayStatus#FAILED}; when no recording
	 * implementation is available the status is
	 * {@link ReplayStatus#UNAVAILABLE}.
	 *
	 * @param config the recording configuration
	 * @return a non-null session to pass to {@link #stopRecording(ReplaySession)}
	 */
	ReplaySession startRecording(ReplayConfig config);

	/**
	 * Stops the recording and uploads it in the background.
	 * <p>
	 * A {@code null} or unknown session is ignored.
	 *
	 * @param session the session returned by
	 *                {@link #startRecording(ReplayConfig)}, or {@code null} to
	 *                do nothing
	 */
	void stopRecording(ReplaySession session);

	/**
	 * @return {@code true} while a recording is active
	 */
	boolean isRecording();

	/**
	 * Reports the character skin the gamemode applied to a player, including a
	 * mid-game change. Playback uses the reported skins; it does not resolve the
	 * player's current skin.
	 * <p>
	 * Call this only when a skin is present. The method is defensive: a
	 * {@code null} player id, a {@code null} texture, a blank texture value or a
	 * blank (unsigned) signature is ignored and never clears a previously
	 * reported skin.
	 *
	 * @param uuid the player's UUID, or {@code null} to do nothing
	 * @param skin the signed texture, or {@code null}/blank/unsigned to do
	 *             nothing
	 */
	void recordPlayerSkin(UUID uuid, @Nullable TexturesProperty skin);

	/**
	 * Returns a snapshot of a recording session, including the replay handle once
	 * one is assigned and the tick reached so far.
	 * <p>
	 * An empty result means no snapshot is available; it does not mean recording
	 * is inactive. Use {@link #isRecording()} for that.
	 *
	 * @param session the session to inspect, or {@code null} to return an empty
	 *                result
	 * @return the snapshot, or empty when no information is available
	 */
	default Optional<ReplayInfo> info(@Nullable ReplaySession session) {
		return Optional.empty();
	}
}
