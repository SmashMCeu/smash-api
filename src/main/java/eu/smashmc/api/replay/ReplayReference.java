package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Snapshot of a stored replay.
 *
 * @param handle        the replay handle
 * @param sessionId     the recording session id, or {@code null} when the
 *                      backend does not persist it
 * @param server        the recording server; empty when unknown
 * @param gamemodeId    the gamemode; empty when unknown
 * @param mapName       the map display name; empty when unknown
 * @param gameId        a game or round id, or {@code null}/empty when none
 * @param players       the roster; never {@code null}, read-only, empty when
 *                      unavailable
 * @param startedAt     when the recording started
 * @param durationTicks the recorded length; {@code 0} when unknown
 * @param status        the replay status
 */
public record ReplayReference(ReplayHandle handle, @Nullable UUID sessionId, String server, String gamemodeId,
							  String mapName, @Nullable String gameId, List<ReplayPlayer> players,
							  Instant startedAt, int durationTicks, ReplayStatus status) {

	public ReplayReference {
		players = players == null ? List.of() : List.copyOf(players);
	}
}
