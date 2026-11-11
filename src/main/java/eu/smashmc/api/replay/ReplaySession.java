package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Identifies one recording session, returned by
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)} and passed back to
 * {@link ReplayGameIntegration#stopRecording(ReplaySession)}.
 * <p>
 * A session is a value: later changes to the recording do not update it. Use
 * {@link ReplayGameIntegration#info(ReplaySession)} for a current snapshot.
 *
 * @param id         unique identifier of this recording session; it is not the
 *                   replay handle and is not stable across sessions
 * @param gamemodeId the gamemode that was recorded, or an empty string when
 *                   unavailable
 * @param handle     the replay handle once one is assigned, or {@code null}
 *                   before that
 * @param status     the session status at the time this value was created
 * @param startedAt  when the recording started
 */
public record ReplaySession(UUID id, String gamemodeId, @Nullable ReplayHandle handle,
							ReplayStatus status, Instant startedAt) {

	/**
	 * Creates a session with no replay handle, status
	 * {@link ReplayStatus#RECORDING} and the current time as start.
	 *
	 * @param id         unique identifier of this recording session
	 * @param gamemodeId the gamemode that is being recorded
	 */
	public ReplaySession(UUID id, String gamemodeId) {
		this(id, gamemodeId, null, ReplayStatus.RECORDING, Instant.now());
	}
}
