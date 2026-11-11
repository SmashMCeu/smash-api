package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Entry point to the replay API, obtained through
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)}. The sub-APIs are never
 * {@code null}; when no provider is registered they are no-ops.
 */
@SmashComponent(value = {Environment.BUKKIT, Environment.BUNGEECORD},
		fallbackImpl = FallbackReplay.class)
public interface Replay {

	/**
	 * @return the recording API; never {@code null}
	 */
	ReplayGameIntegration game();

	/**
	 * @return the replay lookup API; never {@code null}
	 */
	ReplayDirectory directory();

	/**
	 * @return the replay opening API; never {@code null}
	 */
	ReplayPlayback playback();
}
