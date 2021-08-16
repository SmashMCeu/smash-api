package eu.smashmc.api.stats;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * StatsV2 API component.
 * 
 * @author LiquidDev
 *
 */
@SmashComponent({ Environment.BUKKIT, Environment.BUNGEECORD })
public interface Statistics {

	/**
	 * Creates a helper instance that helps you to create and push player
	 * statistics. This can only be used on {@link Environment#BUKKIT} servers!
	 * 
	 * @param gameType the lower case name of the game mode
	 * @return a new {@link StatsHelper} for the gameType
	 * @throws UnsupportedOperationException if not in {@link Environment#BUKKIT}
	 */
	StatsHelper createHelper(String gameType) throws UnsupportedOperationException;

	/**
	 * Retrieve the {@link PlayerStatistics} of a player by their {@link UUID}.
	 * 
	 * @param uuid   the players {@link UUID}
	 * @param period defining the time frame as well as the game type
	 * @return computed {@link PlayerStatistics} for the given period
	 */
	Optional<PlayerStatistics> getStats(UUID uuid, StatsPeriod period);

	/**
	 * Save a {@link GameEntity} to the back-end.
	 * 
	 * @param game the {@link GameEntity} to be saved
	 * @throws IllegalArgumentException if the game is invalid or already exists
	 */
	void saveGame(GameEntity game) throws IllegalArgumentException;

	/**
	 * Retrieve the ranking position of a player in a specific time window using a
	 * sortKey.
	 * 
	 * @param playerUuid the players {@link UUID}
	 * @param period     the {@link StatsPeriod} to query for
	 * @param sortBy     the statistics key to rank by
	 * @return ranking position of the player starting from 0
	 */
	long getPositionOf(UUID playerUuid, StatsPeriod period, String sortBy);

	/**
	 * Retrieve the {@link UUID} of the player that is at the given position for the
	 * given time frame ranked by the sortKey.
	 * 
	 * @param position the position to search for starting from 0
	 * @param period   the {@link StatsPeriod} to query for
	 * @param sortBy   the statistics key to rank by
	 * @return optional {@link UUID} of the player at that position
	 */
	Optional<UUID> getAtPosition(int position, StatsPeriod period, String sortBy);

	/**
	 * Retrieves a {@link List} of {@link UUID} from the top players in the given
	 * time frame ranked by the sortKey.
	 * 
	 * @param limit  limit for the list size
	 * @param period the {@link StatsPeriod} to query for
	 * @param sortBy the statistics key to rank by
	 * @return list a descending {@link List} of {@link UUID} with the very best at
	 *         index 0
	 */
	List<UUID> getTop(int limit, StatsPeriod period, String sortBy);

	/**
	 * Retrieves a list of all game types.
	 * 
	 * @return {@link List} of the game type names
	 */
	List<String> getGameTypes();

	/**
	 * Retrieve a list of all maps from a game.
	 * 
	 * @param gameType
	 * @return {@link List} of {@link GameMap}
	 */
	List<GameMap> getMaps(String gameType);

}
