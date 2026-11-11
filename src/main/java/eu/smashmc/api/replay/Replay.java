package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Entry point to the replay API, obtained through
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)}.
 * <p>
 * {@link ReplayGameIntegration} records games, {@link ReplayDirectory} looks up
 * stored and live replays, and {@link ReplayPlayback} opens a replay for a staff
 * member. A provider may support only some of them; the others are no-ops.
 * <p>
 * {@link #game()}, {@link #directory()} and {@link #playback()} never return
 * {@code null}. When no provider is registered, the game integration reports an
 * inactive recording, directory lookups complete with an empty result, and open
 * requests answer {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}.
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
