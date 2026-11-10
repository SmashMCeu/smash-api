package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.UUID;

/**
 * Point-in-time snapshot of one recording, returned by
 * {@link Replay#info(ReplaySession)}. It is a value object: later changes to
 * the recording do not update it.
 *
 * @param sessionId   the id of the recording session this snapshot describes
 * @param handle      the backend replay handle when it is already known, or
 *                    {@code null} while the recorder has not published one
 * @param gamemodeId  the gamemode id this recording used
 * @param startedAt   when the recording started
 * @param currentTick the tick the recording has reached
 * @param status      the recording status at snapshot time
 */
public record ReplayInfo(UUID sessionId, @Nullable ReplayHandle handle, String gamemodeId,
						 Instant startedAt, int currentTick, ReplayStatus status) {
}
