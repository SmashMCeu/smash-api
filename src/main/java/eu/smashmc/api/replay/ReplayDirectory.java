package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous proxy-side lookup of the replay that contains a player, obtained
 * from {@link Replay#directory()}. The queries go to the backend replay service,
 * so they return a {@link CompletableFuture} and never block the caller
 * (ADR-0064, the {@code PunishmentService} pattern). The no-op fallback
 * completes every lookup with an empty optional.
 * <p>
 * Lookups are keyed by <strong>player + time</strong>, not by server or game:
 * the report entity that consumes this API has no server, round or incident
 * field beyond the player UUID and the report timestamp. No protocol, event ID,
 * snapshot, framing or codec type crosses this boundary (ADR-0007/0009).
 */
public interface ReplayDirectory {

	/**
	 * Resolve a replay by its handle.
	 *
	 * @param handle the backend replay handle
	 * @return a future with the replay reference, or empty when no such replay
	 *         is known; completes exceptionally only for unexpected provider
	 *         failures
	 */
	CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle);

	/**
	 * Resolve the replay that is currently recording the given player.
	 *
	 * @param player the player whose live replay is requested
	 * @return a future with the live replay reference, or empty when the player
	 *         is not in a replay that is recording right now
	 */
	CompletableFuture<Optional<ReplayReference>> findLive(UUID player);

	/**
	 * Resolve the replay whose recording window contains the given instant,
	 * e.g. the report time of a player.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to resolve, e.g.
	 *               {@code Instant.ofEpochMilli(report.getFirstReportStamp())}
	 * @return a future with the matching replay reference, or empty when the
	 *         player was not in a replay at that time
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);
}
