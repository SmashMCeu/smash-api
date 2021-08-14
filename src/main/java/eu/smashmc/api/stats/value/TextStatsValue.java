package eu.smashmc.api.stats.value;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextStatsValue extends StatsValue {

	private String value;

	public TextStatsValue(String value) {
		super(StatsValueType.TEXT);
		this.value = value;
	}
}
