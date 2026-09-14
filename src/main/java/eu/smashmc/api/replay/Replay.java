package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Control surface for recording a game replay, obtained via
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)}. When no recorder is
 * registered, {@code SmashMc} returns the no-op {@link FallbackReplay}.
 * <p>
 * This is the only replay surface in {@code smash-api}: start/stop plus "is one
 * running". No protocol, event IDs, snapshots, framing, or codec types cross
 * this boundary (ADR-0007/0009).
 */
@SmashComponent(value = Environment.BUKKIT, fallbackImpl = FallbackReplay.class)
public interface Replay {

	/**
	 * Begin capture for a game. Called once per game from the server main thread
	 * at game start.
	 *
	 * @param config the gamemode id and reserved header data for this recording
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
}
