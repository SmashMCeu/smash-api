package eu.smashmc.api.replay;

import javax.annotation.Nullable;

/**
 * Outcome of
 * {@link ReplayPlayback#open(net.md_5.bungee.api.connection.ProxiedPlayer, ReplayHandle, int)}.
 *
 * @param status    what happened to the open request
 * @param reference the resolved replay reference when the provider could
 *                  resolve one, otherwise {@code null}
 */
public record ReplayOpenResult(ReplayOpenStatus status, @Nullable ReplayReference reference) {

	/**
	 * Result of asking the proxy to send a staff member into a replay.
	 */
	public enum ReplayOpenStatus {
		/** The staff member was accepted and the transfer/open is in progress. */
		ACCEPTED,
		/** No replay with that handle is known. */
		NO_REPLAY,
		/** The replay exists but is not playable yet (recording, uploading or failed). */
		NOT_READY,
		/** The staff member is not allowed to view this replay. */
		NO_PERMISSION,
		/** No lobby/player server able to host the replay is available. */
		NO_SERVER,
		/** The playback component is unavailable; nothing was attempted. */
		UNAVAILABLE
	}
}
