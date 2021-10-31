package eu.smashmc.api.stats;

import java.time.LocalDateTime;

/**
 * Represents a map from a game with a name and amount of played games on that
 * map.
 * 
 * @author LiquidDev
 *
 */
public interface GameMap {

	/**
	 * Get the name of the map.
	 * 
	 * @return name of the map
	 */
	String getName();

	/**
	 * Get the game type that uses the map, e.g. "smash".
	 * 
	 * @return game type name
	 */
	String getGameType();

	/**
	 * Get the amount of played games on the map.
	 * 
	 * @return amount of games on the map
	 */
	int getGames();

	/**
	 * Get the {@link LocalDateTime} of when the map was played for the first time.
	 * 
	 * @return {@link LocalDateTime} when the map was initially registered
	 */
	LocalDateTime getFirstSeen();

}
