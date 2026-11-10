package eu.smashmc.api.replay;

/**
 * Lifecycle state of one replay.
 * <ul>
 * <li>{@link #RECORDING} — capture is running; the handle may not be known yet</li>
 * <li>{@link #UPLOADING} — capture finished and the file is being uploaded</li>
 * <li>{@link #READY} — the stored replay can be opened</li>
 * <li>{@link #FAILED} — recording or upload failed; the replay is not playable</li>
 * <li>{@link #UNAVAILABLE} — no recorder/provider is available; the fallback
 * value, never a real lifecycle state</li>
 * </ul>
 */
public enum ReplayStatus {
	RECORDING,
	UPLOADING,
	READY,
	FAILED,
	UNAVAILABLE
}
