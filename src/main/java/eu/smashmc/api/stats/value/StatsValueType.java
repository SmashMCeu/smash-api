package eu.smashmc.api.stats.value;

import eu.smashmc.api.stats.value.view.FloatStatsValueAccumulation;
import eu.smashmc.api.stats.value.view.IntStatsValueAccumulation;
import eu.smashmc.api.stats.value.view.StatsValueAccumulation;
import eu.smashmc.api.stats.value.view.TextStatsValueAccumulation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatsValueType {

	TEXT(String.class, TextStatsValueAccumulation.class), INTEGER(Integer.class, IntStatsValueAccumulation.class), FLOAT(Float.class, FloatStatsValueAccumulation.class);

	private final Class<?> valueType;
	private final Class<? extends StatsValueAccumulation> aggregationType;

}
