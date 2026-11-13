package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * No-op {@link ReplayDirectory} returned by {@link FallbackReplay}: every lookup
 * completes with an empty result, and an archive completes exceptionally because
 * nothing is stored.
 */
class FallbackReplayDirectory implements ReplayDirectory {

	@Override
	public CompletableFuture<Optional<ReplayReference>> findByHandle(ReplayHandle handle) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public CompletableFuture<Optional<ReplayReference>> findAt(UUID player, Instant at) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public CompletableFuture<Void> archive(ReplayHandle handle, UUID actor, @Nullable String reason) {
		return CompletableFuture.failedFuture(
				new UnsupportedOperationException("replay archiving is not available"));
	}
}
