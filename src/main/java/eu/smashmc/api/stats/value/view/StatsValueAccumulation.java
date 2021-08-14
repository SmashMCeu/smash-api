package eu.smashmc.api.stats.value.view;

import eu.smashmc.api.stats.value.StatsValueType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class StatsValueAccumulation {

	private final StatsValueType type;

}
