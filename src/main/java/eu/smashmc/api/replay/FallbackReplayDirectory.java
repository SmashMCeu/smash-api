package eu.smashmc.api.replay;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Package-private no-op {@link ReplayDirectory} installed by
 * {@link eu.smashmc.api.SmashMc} when no proxy provider is registered. Every
 * lookup is empty, so consumers never mistake a missing provider for a replay.
 */
class FallbackReplayDirectory implements ReplayDirectory {

	@Override
	public Optional<ReplayReference> findByHandle(ReplayHandle handle) {
		return Optional.empty();
	}

	@Override
	public Optional<ReplayReference> findLive(UUID player) {
		return Optional.empty();
	}

	@Override
	public Optional<ReplayReference> findAt(UUID player, Instant at) {
		return Optional.empty();
	}
}
