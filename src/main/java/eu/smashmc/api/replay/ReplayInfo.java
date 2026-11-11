package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Point-in-time snapshot of one recording, returned by
 * {@link ReplayGameIntegration#info(ReplaySession)}. Later changes to the
 * recording do not update this value.
 *
 * @param sessionId   the id of the recording session this snapshot describes
 * @param handle      the replay handle once one is assigned, or {@code null}
 *                    before that
 * @param gamemodeId  the gamemode that was recorded, or an empty string when
 *                    unavailable
 * @param startedAt   when the recording started
 * @param currentTick the tick the recording has reached; {@code 0} at the start
 * @param status      the recording status at snapshot time
 */
public record ReplayInfo(UUID sessionId, @Nullable ReplayHandle handle, String gamemodeId,
						 Instant startedAt, int currentTick, ReplayStatus status) {
}
