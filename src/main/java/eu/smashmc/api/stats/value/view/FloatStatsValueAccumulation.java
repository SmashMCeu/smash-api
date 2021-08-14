package eu.smashmc.api.stats.value.view;

import eu.smashmc.api.stats.value.StatsValueType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FloatStatsValueAccumulation extends StatsValueAccumulation {

	private long count;
	private double sum;

	public FloatStatsValueAccumulation(long count, double sum) {
		super(StatsValueType.FLOAT);
		this.count = count;
		this.sum = sum;
	}
}
