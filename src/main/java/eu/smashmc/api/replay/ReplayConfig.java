package eu.smashmc.api.replay;

import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Immutable configuration for a recording, passed to
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)}.
 *
 * @param gamemodeId identifier of the gamemode being recorded, e.g.
 *                   {@code "smash"}
 * @param extra      reserved gamemode-specific values; never {@code null}, the
 *                   map is defensively copied and read-only, and it is
 *                   currently ignored
 * @param world      the world to record; capture is limited to it.
 *                   {@code null} uses the server's default world
 * @param gameId     an optional game or round identifier chosen by the
 *                   gamemode, or {@code null} when it has none; informational
 *                   only and an empty value means the same as {@code null}
 */
public record ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world,
						   @Nullable String gameId) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
	}

	/**
	 * Creates a configuration without a capture world or game id.
	 *
	 * @param gamemodeId identifier of the gamemode being recorded
	 * @param extra      reserved gamemode-specific values; never {@code null}
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra) {
		this(gamemodeId, extra, null, null);
	}

	/**
	 * Creates a configuration with a capture world but no game id.
	 *
	 * @param gamemodeId identifier of the gamemode being recorded
	 * @param extra      reserved gamemode-specific values; never {@code null}
	 * @param world      the world to record, or {@code null} for the server's
	 *                   default world
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world) {
		this(gamemodeId, extra, world, null);
	}
}
