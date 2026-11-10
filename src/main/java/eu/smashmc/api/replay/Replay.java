package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Single entry point to the replay domain, obtained via
 * {@link eu.smashmc.api.SmashMc#getComponent(Class)}. It exposes the three
 * sub-APIs of one domain instead of separate components (ADR-0064):
 * {@link ReplayGameIntegration} for the local recorder/gamemode integration,
 * {@link ReplayDirectory} for the asynchronous proxy-side lookup and
 * {@link ReplayPlayback} for the asynchronous proxy-side staff-open request.
 * <p>
 * When no provider is registered, {@code SmashMc} returns the no-op
 * {@link FallbackReplay}, whose sub-APIs are no-ops too: the game integration
 * is idle, the directory always completes empty and playback always answers
 * {@link ReplayOpenResult.ReplayOpenStatus#UNAVAILABLE}. Each
 * platform/role provider exposes the sub-APIs it supports and leaves the
 * others as no-ops.
 * <p>
 * No protocol, event IDs, snapshots, framing or codec types cross this
 * boundary (ADR-0007/0009).
 */
@SmashComponent(value = {Environment.BUKKIT, Environment.BUNGEECORD},
		fallbackImpl = FallbackReplay.class)
public interface Replay {

	/**
	 * @return the local, synchronous recorder/gamemode integration; never
	 *         {@code null}
	 */
	ReplayGameIntegration game();

	/**
	 * @return the asynchronous proxy-side replay lookup; never {@code null}
	 */
	ReplayDirectory directory();

	/**
	 * @return the asynchronous proxy-side staff-open surface; never {@code null}
	 */
	ReplayPlayback playback();
}
