package eu.smashmc.api;

public class PlayerOfflineException extends IllegalStateException {
	public PlayerOfflineException(org.bukkit.entity.Player player) {
		super("Player " + player.getName() + " is offline");
	}
}
