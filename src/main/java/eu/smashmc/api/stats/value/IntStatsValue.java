package eu.smashmc.api.stats.value;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntStatsValue extends StatsValue {

	private int value;

	public IntStatsValue(int value) {
		super(StatsValueType.INTEGER);
		this.value = value;
	}

}
