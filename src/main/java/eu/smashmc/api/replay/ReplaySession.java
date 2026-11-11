package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Recording session, returned by
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)} and passed to
 * {@link ReplayGameIntegration#stopRecording(ReplaySession)}.
 *
 * @param id         the session id; not the replay handle
 * @param gamemodeId the gamemode recorded; may be empty
 * @param handle     the replay handle once assigned; {@code null} before
 * @param status     the status when this value was created
 * @param startedAt  when the recording started
 */
public record ReplaySession(UUID id, String gamemodeId, @Nullable ReplayHandle handle,
							ReplayStatus status, Instant startedAt) {

	/**
	 * Creates a session in {@link ReplayStatus#RECORDING} with no replay handle.
	 */
	public ReplaySession(UUID id, String gamemodeId) {
		this(id, gamemodeId, null, ReplayStatus.RECORDING, Instant.now());
	}
}
