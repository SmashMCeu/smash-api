package eu.smashmc.api.stats.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class StatsValue {

	private StatsValueType type;

}
