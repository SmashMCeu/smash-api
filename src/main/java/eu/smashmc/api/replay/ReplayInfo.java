package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Point-in-time snapshot of a recording, returned by
 * {@link ReplayGameIntegration#info(ReplaySession)}.
 *
 * @param sessionId   the recording session id
 * @param handle      the replay handle once assigned; {@code null} before
 * @param gamemodeId  the gamemode recorded; may be empty
 * @param startedAt   when the recording started
 * @param currentTick the tick reached; {@code 0} at the start
 * @param status      the status at snapshot time
 */
public record ReplayInfo(UUID sessionId, @Nullable ReplayHandle handle, String gamemodeId,
						 Instant startedAt, int currentTick, ReplayStatus status) {
}
