package eu.smashmc.api.replay;

import java.util.Map;

/**
 * Immutable configuration for a recording started via
 * {@link Replay#startRecording(ReplayConfig)}.
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
 */
public record ReplayConfig(String gamemodeId, Map<String, String> extra) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
	}
}
