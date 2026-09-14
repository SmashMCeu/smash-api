package eu.smashmc.api.replay;

import java.util.UUID;

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
}
