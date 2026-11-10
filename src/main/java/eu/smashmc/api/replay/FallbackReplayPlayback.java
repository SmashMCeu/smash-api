package eu.smashmc.api.replay;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.concurrent.CompletableFuture;

/**
 * Package-private no-op {@link ReplayPlayback} installed by
 * {@link eu.smashmc.api.SmashMc} when no proxy provider is registered. Every
 * open request is answered with
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE} without touching the
 * player; it never throws or blocks.
 */
class FallbackReplayPlayback implements ReplayPlayback {

	@Override
	public CompletableFuture<ReplayOpenResult> open(ProxiedPlayer staff, ReplayHandle handle, int tick) {
		return CompletableFuture.completedFuture(
				new ReplayOpenResult(ReplayOpenResult.ReplayOpenStatus.UNAVAILABLE, null));
	}
}
