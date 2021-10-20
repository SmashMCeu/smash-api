package eu.smashmc.api.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonObject;

class FallbackPlayerProfileServiceImpl implements PlayerProfileService {

	private Map<UUID, PlayerProfile> profiles = new HashMap<>();

	@Override
	public PlayerProfile getProfile(UUID uuid) throws IllegalStateException {
		return profiles.computeIfAbsent(uuid, u -> {
			return new PlayerProfile() {
				Map<String, JsonObject> topics = new HashMap<>();

				@Override
				public boolean isReadonly() {
					return false;
				}

				@Override
				public boolean hasTopic(String name) {
					return topics.containsKey(name.toLowerCase());
				}

				@Override
				public UUID getUuid() {
					return u;
				}

				@Override
				public Map<String, JsonObject> getTopics() {
					return topics;
				}

				@Override
				public JsonObject getTopic(String name) {
					return topics.computeIfAbsent(name.toLowerCase(), lower -> new JsonObject());
				}

				@Override
				public JsonObject createTopic(String name) {
					return topics.computeIfAbsent(name.toLowerCase(), lower -> new JsonObject());
				}
			};
		});
	}

	@Override
	public void updateProfile(PlayerProfile profile, String... topics) throws IllegalArgumentException, IllegalStateException {
	}

	@Override
	public CompletableFuture<PlayerProfile> getOfflineProfile(UUID uuid) {
		return CompletableFuture.completedFuture(getProfile(uuid));
	}

}
