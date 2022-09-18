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

	TEXT(TextStatsValue.class, TextStatsValueAccumulation.class), INTEGER(IntStatsValue.class, IntStatsValueAccumulation.class), FLOAT(FloatStatsValue.class, FloatStatsValueAccumulation.class);

	private final Class<? extends StatsValue> valueType;
	private final Class<? extends StatsValueAccumulation> accumulationType;

}
