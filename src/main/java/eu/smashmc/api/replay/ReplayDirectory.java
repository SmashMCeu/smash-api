package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous replay lookup, obtained from {@link Replay#directory()}. Lookups
 * are keyed by player and time; every future never blocks and completes empty
 * when nothing matches.
 */
public interface ReplayDirectory {

	/**
	 * Looks up a replay by handle.
	 *
	 * @param handle the replay handle
	 * @return a future with the reference, or empty when no such replay exists
	 */
	CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle);

	/**
	 * Looks up the replay currently recording a player.
	 *
	 * @param player the player's UUID
	 * @return a future with the reference, or empty when the player is not being
	 *         recorded
	 */
	CompletableFuture<Optional<ReplayReference>> findLive(UUID player);

	/**
	 * Looks up the replay recording at an instant.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to resolve
	 * @return a future with the reference, or empty when no replay covers that
	 *         player and instant
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);
}
