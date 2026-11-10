package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.concurrent.CompletableFuture;

/**
 * Proxy-side request to open a stored replay for a staff member. Obtained via
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)} on the proxy; when no
 * provider is registered, {@code SmashMc} returns the no-op
 * {@link FallbackReplayPlayback}, which always answers
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}.
 * <p>
 * The provider resolves the replay, checks the staff member's replay
 * permission and transfers them to a server able to play it; the player role
 * re-checks the permission before showing the replay. No protocol, event ID,
 * snapshot, framing or codec type crosses this boundary (ADR-0007/0009).
 */
@SmashComponent(value = Environment.BUNGEECORD, fallbackImpl = FallbackReplayPlayback.class)
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
	 * @param staff  the staff member to transfer
	 * @param handle the replay to open
	 * @param tick   the tick to start at; {@code 0} is the first tick of the
	 *               replay
	 * @return a future with the outcome of the open request
	 */
	CompletableFuture<ReplayOpenResult> open(ProxiedPlayer staff, ReplayHandle handle, int tick);
}
