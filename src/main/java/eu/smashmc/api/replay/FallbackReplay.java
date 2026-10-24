package eu.smashmc.api.replay;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Package-private no-op {@link Replay} installed by
 * {@link eu.smashmc.api.SmashMc} when no recorder is registered. Returns
 * dummy/idle values, never throws or NPEs, and never reports a real-looking
 * state (ADR-0009).
 */
class FallbackReplay implements Replay {

	@Override
	public ReplaySession startRecording(ReplayConfig config) {
		return new ReplaySession(UUID.randomUUID(), "");
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
}
