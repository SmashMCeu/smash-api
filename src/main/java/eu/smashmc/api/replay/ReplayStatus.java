package eu.smashmc.api.replay;

/**
 * Lifecycle state of a replay.
 */
public enum ReplayStatus {
	/** Capture is running; a handle may not be available yet. */
	RECORDING,
	/** Capture finished; the replay is uploading and cannot be opened. */
	UPLOADING,
	/** Stored and openable. */
	READY,
	/** Recording or upload failed; not openable. */
	FAILED,
	/** No recording implementation is available. */
	UNAVAILABLE
}
