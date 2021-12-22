package eu.smashmc.api.stats;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Player;

import eu.smashmc.api.stats.value.view.StatsValueAccumulation;

class FallbackStatisticsImpl implements Statistics {

	@Override
	public StatsHelper createHelper(String gameType) throws UnsupportedOperationException {
		return new StatsHelper() {

			@Override
			public void startGame(String mapName, Collection<? extends Player> participants) {
			}

			@Override
			public void setStringValue(String key, Player player, String value) throws IllegalStateException {
			}

			@Override
			public void setIntValue(String key, Player player, int value) throws IllegalStateException {
			}

			@Override
			public void setFloatValue(String key, Player player, float value) throws IllegalStateException {
			}

			@Override
			public void plusIntValue(String key, Player player, int valueToAdd) throws IllegalStateException {
			}

			@Override
			public void plusFloatValue(String key, Player player, float valueToAdd) throws IllegalStateException {
			}

			@Override
			public GameEntity endGame(boolean post) throws IllegalStateException {
				return new GameEntity() {

					@Override
					public LocalDateTime getStartTime() {
						return LocalDateTime.now();
					}

					@Override
					public List<GameParticipant> getParticipants() {
						return Collections.emptyList();
					}

					@Override
					public String getMapName() {
						return "";
					}

					@Override
					public String getGameType() {
						return "";
					}

					@Override
					public LocalDateTime getEndTime() {
						return LocalDateTime.now();
					}
				};
			}

			@Override
			public void addWin(Player player) throws IllegalStateException {
			}

			@Override
			public void addKill(Player player) throws IllegalStateException {
			}

			@Override
			public void addDeath(Player player) throws IllegalStateException {
			}
		};
	}

	@Override
	public Optional<PlayerStatistics> getStats(UUID uuid, StatsPeriod period) {
		return Optional.empty();
	}

	@Override
	public void saveGame(GameEntity game) throws IllegalArgumentException {
	}

	@Override
	public long getPositionOf(UUID playerUuid, StatsPeriod period) {
		return 0;
	}

	@Override
	public Optional<UUID> getAtPosition(int position, StatsPeriod period) {
		return Optional.empty();
	}

	@Override
	public List<UUID> getTop(int limit, StatsPeriod period) {
		return Collections.emptyList();
	}

	@Override
	public List<String> getGameTypes() {
		return Collections.emptyList();
	}

	@Override
	public List<GameMap> getMaps(String gameType) {
		return Collections.emptyList();
	}

	@Override
	public Optional<StatsValueAccumulation> getGlobalStat(String statName, StatsPeriod period) {
		return Optional.empty();
	}
}
