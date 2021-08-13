package eu.smashmc.api.stats;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

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
public interface GameStatistics {

	UUID getPlayerUuid();

	LocalDate getStartDate();

	LocalDate getEndDate();

	String getGameType();

	int getRank();

	Map<String, StatsValueAccumulation> getStats();

	@Nullable
	IntStatsValueAccumulation getIntegerStats(String key) throws IllegalStateException;

	@Nullable
	FloatStatsValueAccumulation getFloatStats(String key) throws IllegalStateException;

	@Nullable
	TextStatsValueAccumulation getTextStats(String key) throws IllegalStateException;
}
