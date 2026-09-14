package eu.smashmc.api.stats;

import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * A simple helper to create and save user statistics.
 *
 * @author LiquidDev
 */
public interface StatsHelper {

	/**
	 * Starts a new game. If a game is already in progress, it will discard the old
	 * game and create a new one with new/empty stats instead.
	 *
	 * @param mapName      Name of the map
	 * @param participants list of all participating players
	 */
	void startGame(String mapName, Collection<? extends Player> participants);

	/**
	 * Ends the running game and optionally posts the statistics to the backend
	 * asynchronously.
	 *
	 * @param postPlayerStats <code>true</code> to post player related stats to backend
	 * @param postMapStats    <code>true</code> to post map related stats to backend
	 * @return The ended game
	 * @throws IllegalStateException If no game is running
	 */
	GameEntity endGame(boolean postPlayerStats, boolean postMapStats) throws IllegalStateException;

	@Deprecated
	default GameEntity endGame(boolean post) throws IllegalStateException {
		return endGame(post, post);
	}

	void addWin(Player player) throws IllegalStateException;

	void addKill(Player player) throws IllegalStateException;

	void addDeath(Player player) throws IllegalStateException;

	void setIntValue(String key, Player player, int value) throws IllegalStateException;

	void setFloatValue(String key, Player player, float value) throws IllegalStateException;

	void setStringValue(String key, Player player, String value) throws IllegalStateException;

	void plusIntValue(String key, Player player, int valueToAdd) throws IllegalStateException;

	void plusFloatValue(String key, Player player, float valueToAdd) throws IllegalStateException;

}
