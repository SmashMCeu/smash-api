package eu.smashmc.api.stats;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import eu.smashmc.api.stats.value.view.FloatStatsValueAccumulation;
import eu.smashmc.api.stats.value.view.IntStatsValueAccumulation;
import eu.smashmc.api.stats.value.view.StatsValueAccumulation;
import eu.smashmc.api.stats.value.view.TextStatsValueAccumulation;

/**
 * Represents the statistics of a player within a specific time frame for a
 * specific game type.
 * 
 * @author LiquidDev
 *
 */
public interface PlayerStatistics {

	/**
	 * Get the {@link UUID} of the player of who the statistics belong to.
	 * 
	 * @return {@link UUID} of the player
	 */
	UUID getPlayerUuid();

	/**
	 * Get the starting date of when the statistics start to count. Might be
	 * {@link LocalDate#EPOCH} for all time statistics.
	 * 
	 * @return {@link LocalDate} of the beginning
	 */
	LocalDate getStartDate();

	/**
	 * Get the ending date of when the statistics start to count.
	 * 
	 * @return {@link LocalDate} of the beginning
	 */
	LocalDate getEndDate();

	/**
	 * Get the name of the game type the statistics are about. This should be the
	 * lower case name of the game mode, e.g. "smash".
	 * 
	 * @return game type of the statistics
	 */
	String getGameType();

	/**
	 * Get the ranking of the player for the game type in the given time frame
	 * starting from 0.
	 * 
	 * @return ranking of the player
	 */
	int getRank();

	/**
	 * Get the actual statistics of the player stored in a {@link Map} mapping the
	 * statistics type name to it's value. An example would be "kills" and
	 * {@link IntStatsValueAccumulation} with sum=5.
	 * 
	 * @return {@link Map} holding {@link String} mapped to
	 *         {@link StatsValueAccumulation}
	 */
	Map<String, StatsValueAccumulation> getStats();

	/**
	 * Gets an {@link IntStatsValueAccumulation} by it's name. If no statistics
	 * value with the given key exists, a new empty
	 * {@link IntStatsValueAccumulation} is created. If the statistic is not of type
	 * {@link IntStatsValueAccumulation}, an {@link IllegalStateException} is
	 * thrown.
	 * 
	 * @param key the name of the statistic, e.g. "kills"
	 * @return {@link IntStatsValueAccumulation} the value of the statistics
	 * @throws IllegalStateException if the statistic is not of type
	 *                               {@link IntStatsValueAccumulation}
	 */
	IntStatsValueAccumulation getIntegerStats(String key) throws IllegalStateException;

	/**
	 * Gets an {@link FloatStatsValueAccumulation} by it's name. If no statistics
	 * value with the given key exists, a new empty
	 * {@link FloatStatsValueAccumulation} is created. If the statistic is not of
	 * type {@link FloatStatsValueAccumulation}, an {@link IllegalStateException} is
	 * thrown.
	 * 
	 * @param key the name of the statistic
	 * @return {@link FloatStatsValueAccumulation} the value of the statistics
	 * @throws IllegalStateException if the statistic is not of type
	 *                               {@link FloatStatsValueAccumulation}
	 */
	FloatStatsValueAccumulation getFloatStats(String key) throws IllegalStateException;

	/**
	 * Gets an {@link TextStatsValueAccumulation} by it's name. If no statistics
	 * value with the given key exists, a new empty
	 * {@link TextStatsValueAccumulation} is created. If the statistic is not of
	 * type {@link TextStatsValueAccumulation}, an {@link IllegalStateException} is
	 * thrown.
	 * 
	 * @param key the name of the statistic
	 * @return {@link TextStatsValueAccumulation} the value of the statistics
	 * @throws IllegalStateException if the statistic is not of type
	 *                               {@link TextStatsValueAccumulation}
	 */
	TextStatsValueAccumulation getTextStats(String key) throws IllegalStateException;
}
