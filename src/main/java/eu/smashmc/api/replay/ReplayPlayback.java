package eu.smashmc.api.replay;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous request to open a stored replay for a viewer, obtained from
 * {@link Replay#playback()}. The provider applies its visibility and permission
 * policy.
 */
public interface ReplayPlayback {

	/**
	 * Opens a replay for a viewer at the given tick. If the viewer is offline,
	 * nothing happens and the future completes with
	 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}; no exception is thrown
	 * and nothing is logged.
	 * <p>
	 * The future never blocks and completes exceptionally only for unexpected
	 * failures, not for the expected {@link ReplayOpenResult.ReplayOpenStatus}
	 * outcomes.
	 *
	 * @param viewer the viewer's UUID
	 * @param handle the replay to open; must not be {@code null}
	 * @param tick   the tick to start at; {@code 0} is the first tick and negative
	 *               values are invalid
	 * @return a future with the outcome; never {@code null}
	 */
	CompletableFuture<ReplayOpenResult> open(UUID viewer, ReplayHandle handle, int tick);
}
