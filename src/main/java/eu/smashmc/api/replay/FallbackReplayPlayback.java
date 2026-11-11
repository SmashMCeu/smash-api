package eu.smashmc.api.replay;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * No-op {@link ReplayPlayback} returned by {@link FallbackReplay}: every request
 * answers {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}.
 */
class FallbackReplayPlayback implements ReplayPlayback {

	@Override
	public CompletableFuture<ReplayOpenResult> open(UUID viewer, ReplayHandle handle, int tick) {
		return CompletableFuture.completedFuture(
				new ReplayOpenResult(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, null));
	}
}
