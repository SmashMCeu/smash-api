package eu.smashmc.api.stats;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent({ Environment.BUKKIT, Environment.BUNGEECORD })
public interface Statistics {

	GameStatistics getStats(UUID uuid, StatsPhase phase);

	void saveGame(GameEntity game);

	Optional<GameEntity> getGame(String id);

	List<GameEntity> getGames(UUID playerUuid);

	long getPositionOf(UUID playerUuid, StatsPhase phase, String sortBy);

	Optional<UUID> getAtPosition(int position, StatsPhase phase, String sortBy);

	List<UUID> getTop(int limit, StatsPhase phase, String sortBy);

}
