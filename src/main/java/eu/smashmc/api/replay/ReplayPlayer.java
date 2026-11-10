package eu.smashmc.api.replay;

import java.util.UUID;

/**
 * One player recorded in a replay, as stored in the replay roster.
 *
 * @param uuid the player's UUID
 * @param name the player's name at recording time
 */
public record ReplayPlayer(UUID uuid, String name) {
}
