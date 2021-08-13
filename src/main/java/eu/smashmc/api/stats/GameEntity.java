package eu.smashmc.api.stats;

import java.time.LocalDateTime;
import java.util.List;

public interface GameEntity {

	String getId();

	String getGameType();

	String getMapName();

	LocalDateTime getStartTime();

	LocalDateTime getEndTime();

	List<GameParticipant> getParticipants();

}
