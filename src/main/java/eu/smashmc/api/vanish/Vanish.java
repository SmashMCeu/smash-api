package eu.smashmc.api.vanish;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.UUID;
import java.util.function.BiPredicate;

/**
 * API for the vanish Plugin.
 *
 * @param <T> type of player
 */
@SmashComponent({Environment.BUKKIT, Environment.BUNGEECORD})
public interface Vanish<T> {

	String defaultMessageChannel = "smashlib:vanish";
	String commandPermission = "proxy.vanish";

	static String getDefaultMessageChannel() {
		return defaultMessageChannel;
	}

	static String getCommandPermission() {
		return commandPermission;
	}

	default boolean isVanished(UUID uuid) {
		return getVanishMode(uuid) == VanishMode.INVISIBLE;
	}

	default boolean isVanished(T player) {
		return getVanishMode(player) == VanishMode.INVISIBLE;
	}

	default boolean isUndercover(UUID uuid) {
		return getVanishMode(uuid) == VanishMode.UNDERCOVER;
	}

	default boolean isUndercover(T player) {
		return getVanishMode(player) == VanishMode.UNDERCOVER;
	}

	default boolean isJustSpectating(UUID uuid) {
		return getVanishMode(uuid) == VanishMode.SPECTATE;
	}

	default boolean isJustSpectating(T player) {
		return getVanishMode(player) == VanishMode.SPECTATE;
	}

	/**
	 * Returns true when the player is either in "vanish" or in "spectate" mode.
	 */
	default boolean shouldExcludeFromGameplay(T player) {
		return getVanishMode(player).shouldExcludeFromGameplay();
	}

	VanishMode getVanishMode(T player);

	VanishMode getVanishMode(UUID uuid);

	/**
	 * Returns the amount of players that are EITHER in "vanish" or in "spectator" mode.
	 */
	int getExcludedFromGameplayPlayerCount();

	int getInvisiblePlayerCount();

	void registerListener(VanishListener<T> listener);

	void removeListener(VanishListener<T> listener);

	/**
	 * Can be used so unvanishing does not cause problems with spectators being
	 * visible. The function should return false if player 1 cannot see player 2.
	 *
	 * @param function function that returns true if the player can see the other
	 *                 one
	 */
	void setCanSeeFunction(BiPredicate<T, T> function);
}