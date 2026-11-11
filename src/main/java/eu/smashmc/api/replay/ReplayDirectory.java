package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous replay lookup, obtained from {@link Replay#directory()}. Every
 * future never blocks; each completes empty when nothing matches and
 * exceptionally when the backend cannot be answered.
 */
public interface ReplayDirectory {

	/**
	 * Looks up a replay keyed by its handle.
	 *
	 * @param handle the replay handle
	 * @return a future with the reference; empty when nothing matches, and
	 *         completed exceptionally when the backend cannot be answered
	 */
	CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle);

	/**
	 * Looks up the newest stored replay keyed by player and instant, whose
	 * recording window covers that instant. Only replays that are ready to be
	 * opened are returned, and a player is recorded by one replay at a time.
	 *
	 * @param player the player the replay must contain
	 * @param at     the instant to resolve
	 * @return a future with the newest matching replay; empty when nothing
	 *         matches, and completed exceptionally when the backend cannot be
	 *         answered
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);
}
