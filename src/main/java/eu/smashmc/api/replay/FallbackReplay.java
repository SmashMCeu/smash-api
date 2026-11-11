package eu.smashmc.api.replay;

/**
 * No-op {@link Replay} used when no provider is registered. Returns the three
 * no-op sub-implementations.
 */
class FallbackReplay implements Replay {

	private final ReplayGameIntegration game = new FallbackReplayGameIntegration();
	private final ReplayDirectory directory = new FallbackReplayDirectory();
	private final ReplayPlayback playback = new FallbackReplayPlayback();

	@Override
	public ReplayGameIntegration game() {
		return game;
	}

	@Override
	public ReplayDirectory directory() {
		return directory;
	}

	@Override
	public ReplayPlayback playback() {
		return playback;
	}
}
