package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous replay lookup and archive, obtained from
 * {@link Replay#directory()}. The futures never block the caller: a lookup
 * completes empty when nothing matches, and every future completes
 * exceptionally when the backend cannot be reached or its response cannot be
 * read.
 */
public interface ReplayDirectory {

	/**
	 * Looks up a replay keyed by its handle.
	 *
	 * @param handle the replay handle
	 * @return a future with the reference; empty when nothing matches, and
	 *         completed exceptionally when the backend cannot be reached or its
	 *         response cannot be read
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
	 *         reached or its response cannot be read
	 */
	CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at);

	/**
	 * Archives a replay so that it is kept permanently: retention never removes
	 * it, and normal delete and modify requests are refused. The operation is
	 * idempotent — a replay that is already archived completes the future
	 * normally.
	 * <p>
	 * The future never blocks the caller. It completes exceptionally when the
	 * backend cannot be reached, the handle is unknown, or the replay is not
	 * stored and ready yet.
	 *
	 * @param handle the replay to archive; must not be {@code null}
	 * @param actor  the staff member performing the archive, or a system UUID
	 * @param reason optional free text for the audit record; a provider may cap
	 *               its length
	 * @return a future that completes once the replay is archived; never
	 *         {@code null}
	 */
	CompletableFuture<Void> archive(ReplayHandle handle, UUID actor, @Nullable String reason);
}
