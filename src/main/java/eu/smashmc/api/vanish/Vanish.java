package eu.smashmc.api.vanish;

import java.util.UUID;
import java.util.function.BiPredicate;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * API for the vanish Plugin.
 * 
 * @param <T> type of player
 */
@SmashComponent({ Environment.BUKKIT, Environment.BUNGEECORD })
public interface Vanish<T> {

	static final String defaultMessageChannel = "smashlib:vanish";
	static final String commandPermission = "proxy.vanish";

	public static String getDefaultMessageChannel() {
		return defaultMessageChannel;
	}

	public static String getCommandPermission() {
		return commandPermission;
	}

	public default boolean isVanished(UUID uuid) {
		return getVanishMode(uuid) == VanishMode.INVISIBLE;
	}

	public default boolean isVanished(T player) {
		return getVanishMode(player) == VanishMode.INVISIBLE;
	}

	public default boolean isUndercover(UUID uuid) {
		return getVanishMode(uuid) == VanishMode.UNDERCOVER;
	}

	public default boolean isUndercover(T player) {
		return getVanishMode(player) == VanishMode.UNDERCOVER;
	}

	public VanishMode getVanishMode(T player);

	public VanishMode getVanishMode(UUID uuid);

	public int getVanishedPlayerCount();

	public void registerListener(VanishListener<T> listener);

	/**
	 * Can be used so unvanishing does not cause problems with spectators being
	 * visible. The function should return false if player 1 cannot see player 2.
	 * 
	 * @param function function that returns true if the player can see the other
	 *                 one
	 */
	public void setCanSeeFunction(BiPredicate<T, T> function);
}