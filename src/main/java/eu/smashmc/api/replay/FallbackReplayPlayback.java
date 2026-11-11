package eu.smashmc.api.replay;

import java.util.concurrent.CompletableFuture;

/**
 * No-op {@link ReplayPlayback} returned by {@link FallbackReplay}: every request
 * answers {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE} without touching
 * the player.
 */
class FallbackReplayPlayback implements ReplayPlayback {

	@Override
	public CompletableFuture<ReplayOpenResult> open(Object staff, ReplayHandle handle, int tick) {
		return CompletableFuture.completedFuture(
				new ReplayOpenResult(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, null));
	}
}
