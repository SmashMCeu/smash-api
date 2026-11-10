package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Package-private no-op {@link ReplayGameIntegration} returned by
 * {@link FallbackReplay}. It reports dummy/idle values, never throws or NPEs,
 * and never reports a real-looking state (ADR-0009): the returned session is
 * {@link ReplayStatus#UNAVAILABLE}, {@link #isRecording()} stays {@code false}
 * and {@link #info(ReplaySession)} stays empty, so those two remain the
 * authority on whether capture is running.
 */
class FallbackReplayGameIntegration implements ReplayGameIntegration {

	@Override
	public ReplaySession startRecording(ReplayConfig config) {
		return new ReplaySession(UUID.randomUUID(), "", null, ReplayStatus.UNAVAILABLE, Instant.now());
	}

	@Override
	public void stopRecording(ReplaySession session) {
	}

	@Override
	public boolean isRecording() {
		return false;
	}

	@Override
	public void recordPlayerSkin(UUID uuid, @Nullable TexturesProperty skin) {
	}

	@Override
	public Optional<ReplayInfo> info(@Nullable ReplaySession session) {
		return Optional.empty();
	}
}
