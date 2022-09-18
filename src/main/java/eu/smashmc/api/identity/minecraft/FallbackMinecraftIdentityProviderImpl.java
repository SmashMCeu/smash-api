package eu.smashmc.api.identity.minecraft;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import lombok.Data;

class FallbackMinecraftIdentityProviderImpl implements MinecraftIdentityProvider {

	private Map<UUID, MinecraftIdentity> idMap = new HashMap<>();

	@Override
	public MinecraftIdentity getById(UUID uuid, boolean loadTexture) {
		return idMap.get(uuid);
	}

	@Override
	public List<MinecraftIdentity> findByName(String name, boolean loadTexture) {
		for (MinecraftIdentity id : idMap.values()) {
			if (name.equalsIgnoreCase(id.getName())) {
				return List.of(id);
			}
		}
		return Collections.emptyList();
	}

	@Override
	public Map<UUID, MinecraftIdentity> getAllById(UUID[] uuids, boolean loadTexture) {
		throw new UnsupportedOperationException("Not supported in fallback mode");
	}

	@Override
	public Map<String, List<MinecraftIdentity>> findAllByName(String[] names, boolean loadTexture) {
		throw new UnsupportedOperationException("Not supported in fallback mode");
	}

	@Override
	public void update(MinecraftIdentity identity) {
		idMap.put(identity.getId(), identity);
	}

	@Data
	class Identity implements MinecraftIdentity {

		String name;
		UUID uuid;
		TexturesProperty texture;

		@Override
		public UUID getId() {
			return uuid;
		}

		@Override
		public boolean isTextureLoaded() {
			return texture != null;
		}

		@Override
		public TexturesProperty getTexture() throws IllegalStateException {
			return texture;
		}

		@Override
		public TexturesProperty getTextureOrNull() {
			return texture;
		}

		@Override
		public boolean isOnline() {
			return true;
		}

		@Override
		public String getServer() {
			return "";
		}
	}

	@Override
	public CompletableFuture<Long> count() {
		return CompletableFuture.completedFuture(0L);
	}
}
