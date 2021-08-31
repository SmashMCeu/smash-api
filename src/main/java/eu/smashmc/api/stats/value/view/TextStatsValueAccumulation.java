package eu.smashmc.api.stats.value.view;

import java.util.Map;

import eu.smashmc.api.stats.value.StatsValueType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TextStatsValueAccumulation extends StatsValueAccumulation {

	/*
	 * A map containing every value mapped to their count of occurrences.
	 */
	private Map<String, Integer> valueDistribution;

	public TextStatsValueAccumulation(Map<String, Integer> valueDistribution) {
		super(StatsValueType.TEXT);
		this.valueDistribution = valueDistribution;
	}

}
