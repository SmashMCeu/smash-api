package eu.smashmc.api.replay;

import java.util.UUID;

/**
 * Opaque handle for one recording session, returned by
 * {@link Replay#startRecording(ReplayConfig)} and passed back to
 * {@link Replay#stopRecording(ReplaySession)}.
 *
 * @param id         unique identifier of this recording session; the recorder
 *                   keys its active session by it. This is not the
 *                   backend-assigned replay id — {@code replay-service} assigns
 *                   the replay id on upload, and the API must not couple to it
 * @param gamemodeId the gamemode id from the config for this session
 */
public record ReplaySession(UUID id, String gamemodeId) {
}
