package eu.smashmc.api.stats;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a single game/match with a unique id.
 * 
 * @author LiquidDev
 *
 */
public interface GameEntity {

	/**
	 * The name of the game type the entity belongs to. This should be the lower
	 * case name of the game mode, e.g. "smash".
	 * 
	 * @return game type name
	 */
	String getGameType();

	/**
	 * Name of the map used for the game.
	 * 
	 * @return name of the map
	 */
	String getMapName();

	/**
	 * Time when the game started.
	 * 
	 * @return starting time of the game
	 */
	LocalDateTime getStartTime();

	/**
	 * Time when the game ended.
	 * 
	 * @return ending time of the game
	 */
	LocalDateTime getEndTime();

	/**
	 * {@link List} of all participants of the game.
	 * 
	 * @return {@link List} of {@link GameParticipant}
	 */
	List<GameParticipant> getParticipants();

}
