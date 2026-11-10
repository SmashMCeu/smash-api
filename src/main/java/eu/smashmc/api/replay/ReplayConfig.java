package eu.smashmc.api.replay;

import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Immutable configuration for a recording started via
 * {@link ReplayGameIntegration#startRecording(ReplayConfig)}.
 *
 * @param gamemodeId stable gamemode identifier recorded in the replay header
 *                   (e.g. {@code "smash"}); informational in v1, capture is
 *                   gamemode-agnostic
 * @param extra      reserved for future gamemode-specific, non-network header
 *                   data — information a viewer may need that cannot be
 *                   reconstructed from captured packets. Must be empty in v1:
 *                   the file format reserves a length-prefixed {@code extra}
 *                   header field but defines no keys, and the recorder ignores
 *                   this map. Never {@code null} (normalized to an empty map)
 *                   and defensively copied on construction; keys and values are
 *                   opaque strings owned by the gamemode. Note the type
 *                   distinction: the blob header stores {@code extra} as a raw
 *                   {@code byte[]}, while this API exposes a
 *                   {@code Map<String,String>}; the mapping between them is
 *                   intentionally left undefined until a future format version
 *                   defines it.
 * @param world      the runtime capture scope chosen by the gamemode, i.e. the
 *                   world the players are actually playing in; the recorder
 *                   limits snapshots, roster, packets, chat and broadcast
 *                   capture to it. This is <em>not</em> header or file data and
 *                   is never serialized — playback resolves the map by version
 *                   id. May be {@code null} for callers that do not care; the
 *                   recorder then falls back to the server default world
 * @param gameId     a nullable game/round identifier chosen by the gamemode,
 *                   used to correlate a replay with the game it recorded (e.g.
 *                   the match or round key the gamemode already uses). It is
 *                   informational and never serialized by the recorder; the
 *                   recording window itself is defined by the replay session
 */
public record ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world,
						   @Nullable String gameId) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
	}

	/**
	 * Creates a configuration without an explicit capture world or game id.
	 *
	 * @param gamemodeId stable gamemode identifier recorded in the replay header
	 * @param extra      reserved for future gamemode-specific, non-network header
	 *                   data; never {@code null}
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra) {
		this(gamemodeId, extra, null, null);
	}

	/**
	 * Creates a configuration with a capture world but no game id.
	 *
	 * @param gamemodeId stable gamemode identifier recorded in the replay header
	 * @param extra      reserved for future gamemode-specific, non-network header
	 *                   data; never {@code null}
	 * @param world      the runtime capture scope, or {@code null} for the
	 *                   recorder's default world
	 */
	public ReplayConfig(String gamemodeId, Map<String, String> extra, @Nullable World world) {
		this(gamemodeId, extra, world, null);
	}
}
