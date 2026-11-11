package eu.smashmc.api.replay;

import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Configuration for a recording, passed to
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)}.
 *
 * @param gamemodeId the gamemode being recorded
 * @param extra      reserved; never {@code null}, defensively copied and
 *                   read-only
 * @param world      the world to record; {@code null} uses the server's default
 *                   world
 * @param gameId     a game or round id, or {@code null}/empty when none
 */
public record ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world,
						   @Nullable String gameId) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
	}

	/**
	 * Creates a configuration without a capture world or game id.
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra) {
		this(gamemodeId, extra, null, null);
	}

	/**
	 * Creates a configuration with a capture world and no game id.
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world) {
		this(gamemodeId, extra, world, null);
	}
}
