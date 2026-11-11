package eu.smashmc.api.replay;

import java.util.concurrent.CompletableFuture;

/**
 * Opens stored replays for staff members, obtained from
 * {@link Replay#playback()}.
 * <p>
 * The request resolves the replay, checks whether the staff member may view it
 * and transfers them to a server able to play it; the player server checks the
 * permission again before showing the replay.
 */
public interface ReplayPlayback {

	/**
	 * Sends a staff member into a replay and starts it at the given tick.
	 * <p>
	 * The returned future completes when the request is accepted or rejected. It
	 * never blocks the caller and completes exceptionally only for unexpected
	 * failures, not for the expected
	 * {@link ReplayOpenResult.ReplayOpenStatus} outcomes.
	 *
	 * @param staff  the player to transfer; the replay proxy expects a Bungee
	 *               {@code ProxiedPlayer} and casts it, so pass that type
	 * @param handle the replay to open; must not be {@code null}
	 * @param tick   the replay tick to start at; {@code 0} is the first tick and
	 *               negative values are invalid
	 * @return a future with the outcome, never {@code null}
	 */
	CompletableFuture<ReplayOpenResult> open(Object staff, ReplayHandle handle, int tick);
}
