package eu.smashmc.api.replay;

import javax.annotation.Nullable;

/**
 * Outcome of {@link ReplayPlayback#open(Object, ReplayHandle, int)}.
 *
 * @param status    what happened to the open request
 * @param reference the resolved replay, or {@code null} when no replay could be
 *                  resolved
 */
public record ReplayOpenResult(ReplayOpenStatus status, @Nullable ReplayReference reference) {

	/**
	 * Result of an open request.
	 */
	public enum ReplayOpenStatus {
		/** The request was accepted; the transfer and open are in progress. */
		ACCEPTED,
		/** No replay with the given handle exists. */
		NO_REPLAY,
		/** The replay exists but is still recording or uploading, or failed. */
		NOT_READY,
		/** The staff member may not view this replay. */
		NO_PERMISSION,
		/** No server able to play the replay is available. */
		NO_SERVER,
		/** Playback is unavailable; the request was not attempted. */
		UNAVAILABLE
	}
}
