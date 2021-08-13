package eu.smashmc.api.stats;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import eu.smashmc.api.stats.value.StatsValue;

public interface GameParticipant {

	UUID getUuid();

	LocalDateTime getFinishTime();

	Map<String, StatsValue> getStatistics();

}
