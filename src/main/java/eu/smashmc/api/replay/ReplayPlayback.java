package eu.smashmc.api.replay;

import java.util.concurrent.CompletableFuture;

/**
 * Proxy-side request to open a stored replay for a staff member, obtained from
 * {@link Replay#playback()}. The provider resolves the replay, checks the staff
 * member's replay permission and transfers them to a server able to play it;
 * the player role re-checks the permission before showing the replay. The no-op
 * fallback always answers
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}.
 * <p>
 * No protocol, event ID, snapshot, framing or codec type crosses this boundary
 * (ADR-0007/0009).
 */
public interface ReplayPlayback {

	/**
	 * Ask the proxy to send a staff member into a replay and start it at the
	 * given tick.
	 * <p>
	 * The returned future completes when the request has been accepted or
	 * rejected; it never blocks the caller and completes exceptionally only for
	 * unexpected provider failures, not for the expected
	 * {@link ReplayOpenResult.ReplayOpenStatus} outcomes.
	 *
	 * @param staff  the platform player object to transfer: a Bungee
	 *               {@code ProxiedPlayer} when the proxy provider runs, which
	 *               casts it; the reports plugin passes the {@code ProxiedPlayer}
	 * @param handle the replay to open
	 * @param tick   the tick to start at; {@code 0} is the first tick of the
	 *               replay
	 * @return a future with the outcome of the open request
	 */
	CompletableFuture<ReplayOpenResult> open(Object staff, ReplayHandle handle, int tick);
}
