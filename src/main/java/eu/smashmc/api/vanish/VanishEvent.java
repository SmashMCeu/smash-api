package eu.smashmc.api.vanish;

import java.util.UUID;

/**
 * @param <T> type of player
 */
public class VanishEvent<T> {

	private T player;
	private UUID playerUuid;
	private VanishMode vanishMode;

	public VanishEvent(T player, UUID uuid, VanishMode vanishMode) {
		this.player = player;
		this.playerUuid = uuid;
		this.vanishMode = vanishMode;
	}

	public T getPlayer() {
		return player;
	}

	public UUID getPlayerUuid() {
		return playerUuid;
	}

	public boolean hasVanished() {
		return vanishMode == VanishMode.INVISIBLE;
	}

	public boolean isUndercover() {
		return vanishMode == VanishMode.UNDERCOVER;
	}
}