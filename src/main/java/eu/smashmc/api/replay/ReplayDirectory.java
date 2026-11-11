package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous lookup of stored and live replays, obtained from
 * {@link Replay#directory()}.
 * <p>
 * Every method returns a {@link CompletableFuture} and never blocks the calling
 * thread. The future completes with {@link Optional#empty()} when nothing
 * matches, and completes exceptionally only for unexpected lookup failures.
 */
public interface ReplayDirectory {

	/**
	 * Looks up a replay by its handle.
	 *
	 * @param handle the replay handle
	 * @return a future with the matching reference, or empty when no such replay
	 *         is known
	 */
	CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle);

	/**
	 * Looks up the replay that is currently recording a player.
	 *
	 * @param player the player's UUID
	 * @return a future with the live reference, or empty when the player is not
	 *         being recorded
	 */
	CompletableFuture<Optional<ReplayReference>> findLive(UUID player);

	/**
	 * Looks up the replay whose recording covers the given instant.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to look up
	 * @return a future with the matching reference, or empty when no replay
	 *         covers that player and instant
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);
}
