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
	 * Requests that a replay is opened for a viewer at the given tick. The
	 * provider clamps the tick to the replay's range.
	 * <p>
	 * The result describes the outcome of this request, not the viewer's state:
	 * an accepted request does not mean that the viewer is already watching. If
	 * the viewer is offline, nothing happens and the future completes with
	 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}; no exception is thrown
	 * and nothing is logged.
	 * <p>
	 * The future never blocks. It completes exceptionally only for unexpected
	 * failures, including a backend that cannot be answered, and never for the
	 * expected {@link ReplayOpenResult.ReplayOpenStatus} outcomes.
	 *
	 * @param viewer the viewer's UUID
	 * @param handle the replay to open; must not be {@code null}
	 * @param tick   the tick to start at; {@code 0} is the first tick, and the
	 *               provider clamps the value to the replay's range
	 * @return a future with the request outcome; never {@code null}
	 */
	CompletableFuture<ReplayOpenResult> open(UUID viewer, ReplayHandle handle, int tick);
}
