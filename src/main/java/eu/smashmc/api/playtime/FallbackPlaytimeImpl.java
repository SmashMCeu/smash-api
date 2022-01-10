package eu.smashmc.api.playtime;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;

class FallbackPlaytimeImpl implements Playtime {

	@Override
	public CompletableFuture<PlaytimeInfo> getPlaytimeInfo(UUID uuid) {
		return CompletableFuture.completedFuture(new Info(uuid, ""));
	}

	@Override
	public void addPlaytime(UUID uuid, long playtime) {
	}

	@AllArgsConstructor
	class Info implements PlaytimeInfo {

		UUID uuid;
		String name;

		@Override
		public long getPlaytime(TimeUnit unit) {
			return 0;
		}

		@Override
		public LocalDateTime getLastSeen() {
			return LocalDateTime.now();
		}

		@Override
		public LocalDateTime getFirstSeen() {
			return LocalDateTime.now();
		}

		@Override
		public UUID getUuid() {
			return uuid;
		}
	}

	@Override
	public boolean isCountingPlaytime(UUID uuid) {
		return false;
	}
}
