package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * No-op {@link ReplayGameIntegration} returned by {@link FallbackReplay}: an
 * inactive session, no recorded skins and no live snapshot.
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
