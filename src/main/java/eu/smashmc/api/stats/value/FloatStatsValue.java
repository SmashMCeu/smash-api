package eu.smashmc.api.stats.value;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FloatStatsValue extends StatsValue {

	private float value;

	public FloatStatsValue(float value) {
		super(StatsValueType.FLOAT);
		this.value = value;
	}
}
