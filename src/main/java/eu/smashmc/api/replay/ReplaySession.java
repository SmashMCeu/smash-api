package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Opaque handle for one recording session, returned by
 * {@link Replay#startRecording(ReplayConfig)} and passed back to
 * {@link Replay#stopRecording(ReplaySession)}.
 * <p>
 * The first two components are the original session identity. The remaining
 * components are a live view of the recording that consumers can read without
 * talking to the recorder: the backend handle (once assigned), the lifecycle
 * status and the recording start. A recorder that does not track live handles
 * may leave {@link #handle()} {@code null}; {@link Replay#info(ReplaySession)}
 * is the authoritative snapshot.
 *
 * @param id         unique identifier of this recording session; the recorder
 *                   keys its active session by it. This is not the
 *                   backend-assigned replay id — {@code replay-service} assigns
 *                   the replay id on upload, and the API must not couple to it
 * @param gamemodeId the gamemode id this recording used (resolved and
 *                   normalized by the recorder)
 * @param handle     the backend replay handle when it is already known, or
 *                   {@code null} while the recorder has not published one
 * @param status     the recording status at the time this session value was
 *                   created
 * @param startedAt  when the recording started
 */
public record ReplaySession(UUID id, String gamemodeId, @Nullable ReplayHandle handle,
							ReplayStatus status, Instant startedAt) {

	/**
	 * Creates a session value for a recording that just started and has no
	 * backend handle yet. Source-compatible with the pre-v2 API.
	 *
	 * @param id         unique identifier of this recording session
	 * @param gamemodeId the gamemode id this recording used
	 */
	public ReplaySession(UUID id, String gamemodeId) {
		this(id, gamemodeId, null, ReplayStatus.RECORDING, Instant.now());
	}
}
