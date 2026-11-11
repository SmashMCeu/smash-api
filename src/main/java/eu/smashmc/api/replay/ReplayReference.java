package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Snapshot of a stored replay's identity and state, used to decide whether and
 * where the replay can be opened.
 *
 * @param handle        the replay handle
 * @param sessionId     the id of the recording session that produced the replay
 * @param server        the server that recorded the replay, or an empty string
 *                      when unknown
 * @param gamemodeId    the gamemode that was recorded, or an empty string when
 *                      unknown
 * @param mapName       the display name of the map that was played, or an empty
 *                      string when unknown
 * @param gameId        the game or round id, or {@code null} when none was
 *                      provided; an empty value means the same as {@code null}
 * @param players       the recorded players; never {@code null}, read-only, and
 *                      empty when the roster is unavailable
 * @param startedAt     when the recording started
 * @param currentTick   the tick the recording has reached
 * @param durationTicks the total length of a finished replay, the current length
 *                      while it is still recording or uploading, or {@code 0}
 *                      when unknown
 * @param status        the replay status
 */
public record ReplayReference(ReplayHandle handle, UUID sessionId, String server, String gamemodeId,
							  String mapName, @Nullable String gameId, List<ReplayPlayer> players,
							  Instant startedAt, int currentTick, int durationTicks, ReplayStatus status) {

	public ReplayReference {
		players = players == null ? List.of() : List.copyOf(players);
	}
}
