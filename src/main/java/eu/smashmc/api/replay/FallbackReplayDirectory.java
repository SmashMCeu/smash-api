package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Package-private no-op {@link ReplayDirectory} returned by
 * {@link FallbackReplay}. Every lookup completes with an empty optional, so
 * consumers never mistake a missing provider for a replay, and no lookup can
 * block or fail.
 */
class FallbackReplayDirectory implements ReplayDirectory {

	@Override
	public CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public CompletableFuture<Optional<ReplayReference>> findLive(UUID player) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at) {
		return CompletableFuture.completedFuture(Optional.empty());
	}
}
