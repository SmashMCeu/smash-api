package eu.smashmc.api.stats.value.view;

import eu.smashmc.api.stats.value.StatsValueType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IntStatsValueAccumulation extends StatsValueAccumulation {

	private long count;
	private long sum;

	public IntStatsValueAccumulation(long count, long sum) {
		super(StatsValueType.INTEGER);
		this.count = count;
		this.sum = sum;
	}

}
