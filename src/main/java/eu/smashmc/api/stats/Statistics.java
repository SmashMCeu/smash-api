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
	public StatsHelper createHelper(String gameType) throws UnsupportedOperationException;

	/**
	 * Retrieve the {@link GameStatistics} of a player by their {@link UUID}.
	 * 
	 * @param uuid  the players {@link UUID}
	 * @param phase defining the time frame as well as the game type
	 * @return computed {@link GameStatistics} for the given phase
	 */
	Optional<GameStatistics> getStats(UUID uuid, StatsPhase phase);

	/**
	 * Save a {@link GameEntity} to the back-end.
	 * 
	 * @param game the {@link GameEntity} to be saved
	 * @throws IllegalArgumentException if the game is invalid or already exists
	 */
	void saveGame(GameEntity game) throws IllegalArgumentException;

	/**
	 * Retrieve a {@link GameEntity} by their id.
	 * 
	 * @param id the games id
	 * @return optional {@link GameEntity}
	 */
	Optional<GameEntity> getGame(String id);

	/**
	 * Retrieve a {@link List} of all games the given player participated in. This
	 * might be a very large collection depending, use with care.
	 * 
	 * @param playerUuid the player {@link UUID}
	 * @return a list of all games the given player participated in
	 */
	List<GameEntity> getGames(UUID playerUuid);

	/**
	 * Retrieve the ranking position of a player in a specific time window using a
	 * sortKey.
	 * 
	 * @param playerUuid the players {@link UUID}
	 * @param phase      the {@link StatsPhase} to query for
	 * @param sortBy     the statistics key to rank by
	 * @return ranking position of the player starting from 0
	 */
	long getPositionOf(UUID playerUuid, StatsPhase phase, String sortBy);

	/**
	 * Retrieve the {@link UUID} of the player that is at the given position for the
	 * given time frame ranked by the sortKey.
	 * 
	 * @param position the position to search for starting from 0
	 * @param phase    the {@link StatsPhase} to query for
	 * @param sortBy   the statistics key to rank by
	 * @return optional {@link UUID} of the player at that position
	 */
	Optional<UUID> getAtPosition(int position, StatsPhase phase, String sortBy);

	/**
	 * Retrieves a {@link List} of {@link UUID} from the top players in the given
	 * time frame ranked by the sortKey.
	 * 
	 * @param limit  limit for the list size
	 * @param phase  the {@link StatsPhase} to query for
	 * @param sortBy the statistics key to rank by
	 * @return list a descending {@link List} of {@link UUID} with the very best at
	 *         index 0
	 */
	List<UUID> getTop(int limit, StatsPhase phase, String sortBy);

	/**
	 * Retrieves a list of all game types.
	 * 
	 * @return {@link List} of the game type names
	 */
	public List<String> getGameTypes();

}
