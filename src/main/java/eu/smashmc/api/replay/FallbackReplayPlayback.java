package eu.smashmc.api.replay;

import java.util.concurrent.CompletableFuture;

/**
 * Package-private no-op {@link ReplayPlayback} returned by
 * {@link FallbackReplay}. Every open request is answered with
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE} without touching the
 * player; it never throws or blocks.
 */
class FallbackReplayPlayback implements ReplayPlayback {

	@Override
	public CompletableFuture<ReplayOpenResult> open(Object staff, ReplayHandle handle, int tick) {
		return CompletableFuture.completedFuture(
				new ReplayOpenResult(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, null));
	}
}
