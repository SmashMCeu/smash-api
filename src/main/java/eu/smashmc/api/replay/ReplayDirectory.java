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
	 * Looks up the newest stored replay whose recording window covers an instant.
	 * Only replays that are ready to be opened are returned, and a player is
	 * recorded by one replay at a time.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to resolve
	 * @return a future with the newest matching replay, or empty when no stored
	 *         replay covers that player and instant
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);
}
