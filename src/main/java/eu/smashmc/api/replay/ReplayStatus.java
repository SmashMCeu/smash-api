package eu.smashmc.api.replay;

/**
 * Lifecycle state of one replay.
 * <ul>
 * <li>{@link #RECORDING} — capture is running; a replay handle may not be
 * available yet</li>
 * <li>{@link #UPLOADING} — capture finished and the replay is being uploaded;
 * it cannot be opened yet</li>
 * <li>{@link #READY} — the replay is stored and can be opened</li>
 * <li>{@link #FAILED} — recording or upload failed; the replay cannot be
 * opened</li>
 * <li>{@link #UNAVAILABLE} — no recording implementation is available; returned
 * when the API runs without one</li>
 * </ul>
 */
public enum ReplayStatus {
	RECORDING,
	UPLOADING,
	READY,
	FAILED,
	UNAVAILABLE
}
