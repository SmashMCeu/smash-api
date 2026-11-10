package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Proxy-side lookup of the replay that contains a player. Obtained via
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)} on the proxy; when no
 * provider is registered, {@code SmashMc} returns the no-op
 * {@link FallbackReplayDirectory} whose lookups are always empty.
 * <p>
 * Lookups are keyed by <strong>player + time</strong>, not by server or game:
 * the report entity that consumes this API has no server, round or incident
 * field beyond the player UUID and the report timestamp. No protocol, event ID,
 * snapshot, framing or codec type crosses this boundary (ADR-0007/0009).
 */
@SmashComponent(value = Environment.BUNGEECORD, fallbackImpl = FallbackReplayDirectory.class)
public interface ReplayDirectory {

	/**
	 * Resolve a replay by its handle.
	 *
	 * @param handle the backend replay handle
	 * @return the replay reference, or empty when no such replay is known
	 */
	Optional<ReplayReference> findByHandle(ReplayHandle handle);

	/**
	 * Resolve the replay that is currently recording the given player.
	 *
	 * @param player the player whose live replay is requested
	 * @return the live replay reference, or empty when the player is not in a
	 *         replay that is recording right now
	 */
	Optional<ReplayReference> findLive(UUID player);

	/**
	 * Resolve the replay whose recording window contains the given instant,
	 * e.g. the report time of a player.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to resolve, e.g. {@code Instant.ofEpochMilli(report.getFirstReportStamp())}
	 * @return the matching replay reference, or empty when the player was not in
	 *         a replay at that time
	 */
	Optional<ReplayReference> findAt(UUID player, Instant at);
}
