package eu.smashmc.api.replay;

import javax.annotation.Nullable;

/**
 * Outcome of {@link ReplayPlayback#open(java.util.UUID, ReplayHandle, int)}.
 *
 * @param status    the outcome
 * @param reference the resolved replay, or {@code null} when unresolved
 */
public record ReplayOpenResult(ReplayOpenStatus status, @Nullable ReplayReference reference) {

	/**
	 * Result of an open request.
	 */
	public enum ReplayOpenStatus {
		/** The request was accepted; transfer and open are in progress. */
		ACCEPTED,
		/** No replay with the given handle exists. */
		NO_REPLAY,
		/** The replay exists but is still recording or uploading, or failed. */
		NOT_READY,
		/** The viewer is not allowed by the provider's visibility/permission policy. */
		NO_PERMISSION,
		/** No server able to play the replay is available. */
		NO_SERVER,
		/** The request was not attempted; playback is unavailable or the viewer is offline. */
		UNAVAILABLE
	}
}
