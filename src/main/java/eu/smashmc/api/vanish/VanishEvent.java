package eu.smashmc.api.vanish;

import java.util.UUID;

/**
 * @param player     the player that vanished
 * @param playerUuid the {@link UUID} of the player
 * @param vanishMode the vanish mode changed to
 * @param <T>        type of player
 */
public record VanishEvent<T> (T player, UUID playerUuid, VanishMode vanishMode) {

	public boolean hasVanished() {
		return vanishMode == VanishMode.INVISIBLE;
	}

	public boolean isUndercover() {
		return vanishMode == VanishMode.UNDERCOVER;
	}
}