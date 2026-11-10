package eu.smashmc.api.replay;

/**
 * Package-private no-op {@link Replay} installed by
 * {@link eu.smashmc.api.SmashMc} when no provider is registered. It returns the
 * three no-op sub-implementations and never throws or NPEs, so consumers can
 * always dereference the sub-APIs (ADR-0009/0064): the game integration is
 * idle, the directory always completes empty and playback always answers
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}.
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
