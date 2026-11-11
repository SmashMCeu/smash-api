package eu.smashmc.api.replay;

import java.util.UUID;

/**
 * A player in a replay roster.
 *
 * @param uuid the player's UUID
 * @param name the name at recording time
 */
public record ReplayPlayer(UUID uuid, String name) {
}
