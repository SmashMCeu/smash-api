package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Stable reference to one replay as seen by a proxy-side consumer. It carries
 * the display and validation data needed to decide whether and where the
 * replay can be opened, without exposing any protocol or codec types.
 *
 * @param handle        the backend replay handle
 * @param sessionId     the recording session id
 * @param server        the game server that recorded the replay
 * @param gamemodeId    the gamemode id this recording used
 * @param mapName       the display name of the map that was played
 * @param gameId        the nullable game/round id chosen by the gamemode
 * @param players       the players recorded in the replay roster
 * @param startedAt     when the recording started
 * @param currentTick   the tick the recording has reached
 * @param durationTicks the total length of a finished replay, or the current
 *                      length while it is still recording/uploading
 * @param status        the replay lifecycle status
 */
public record ReplayReference(ReplayHandle handle, UUID sessionId, String server, String gamemodeId,
							  String mapName, @Nullable String gameId, List<ReplayPlayer> players,
							  Instant startedAt, int currentTick, int durationTicks, ReplayStatus status) {

	public ReplayReference {
		players = players == null ? List.of() : List.copyOf(players);
	}
}
