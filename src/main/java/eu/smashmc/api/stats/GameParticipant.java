package eu.smashmc.api.stats;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import eu.smashmc.api.stats.value.StatsValue;

/**
 * Represents a player that participated in a game.
 * 
 * @author LiquidDev
 *
 */
public interface GameParticipant {

	/**
	 * The {@link UUID} of the player that participated in the game.
	 * 
	 * @return {@link UUID} of the participating player
	 */
	UUID getUuid();

	/**
	 * Time when the participant finished the match. Might be earlier as the time in
	 * the parenting game, for e.g. when the player died early.
	 * 
	 * @return time the participant finished the game
	 */
	LocalDateTime getFinishTime();

	/**
	 * The participants statistics of a single game.
	 * 
	 * Returned is a {@link Map} holding the name of a statistic mapped to their
	 * {@link StatsValue}.
	 * 
	 * @return {@link Map} statistics name mapped to their {@link StatsValue}
	 */
	Map<String, StatsValue> getStatistics();

}
