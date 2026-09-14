package eu.smashmc.api.replay;

import java.util.Map;

public record ReplayConfig(String gamemodeId, Map<String, String> extra) {

	public ReplayConfig {
		extra = extra == null ? Map.of() : Map.copyOf(extra);
	}
}
