package eu.smashmc.api.replay;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Configuration for a recording, passed to
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)}.
 *
 * @param gamemodeId   the gamemode being recorded
 * @param extra        reserved; never {@code null}, defensively copied and
 *                     read-only
 * @param worldName    the name of the world to record; {@code null} uses the
 *                     server's default world
 * @param gameId       a game or round id, or {@code null}/empty when none
 * @param participants the players who are in the game when the recording
 *                     starts; empty means the participants are not reported
 *                     and every recorded player counts as one. Metadata only,
 *                     never part of the recorded file; never {@code null},
 *                     defensively copied and read-only
 */
public record ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable String worldName,
						   @Nullable String gameId, Set<UUID> participants) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
		participants = participants == null ? Set.of() : Set.copyOf(participants);
	}

	/**
	 * Creates a configuration without a capture world, a game id or reported
	 * participants.
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra) {
		this(gamemodeId, extra, null, null, Set.of());
	}

	/**
	 * Creates a configuration with a capture world and no game id or reported
	 * participants.
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable String worldName) {
		this(gamemodeId, extra, worldName, null, Set.of());
	}

	/**
	 * Creates a configuration without reported participants.
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable String worldName,
						@Nullable String gameId) {
		this(gamemodeId, extra, worldName, gameId, Set.of());
	}
}
