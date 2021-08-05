package eu.smashmc.api.identity.minecraft;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.concurrent.AsyncExecutor;
import eu.smashmc.api.identity.IdentityProvider;

/**
 * A {@link UUID} to name, name to {@link UUID} and skin provider for Minecraft
 * players.
 * 
 * @author LiquidDev
 */
@SmashComponent
public interface MinecraftIdentityProvider extends IdentityProvider<UUID, MinecraftIdentity> {

	@Nullable
	MinecraftIdentity getById(UUID uuid, boolean loadTexture);

	List<MinecraftIdentity> findByName(String name, boolean loadTexture);

	Map<UUID, MinecraftIdentity> getAllById(UUID[] uuids, boolean loadTexture);

	Map<String, List<MinecraftIdentity>> findAllByName(String[] names, boolean loadTexture);

	@Nullable
	@Override
	default MinecraftIdentity getById(UUID uuid) {
		return this.getById(uuid, false);
	}

	@Override
	default List<MinecraftIdentity> findByName(String name) {
		return findByName(name, false);
	}

	@Override
	default Map<UUID, MinecraftIdentity> getAllById(UUID[] uuids) {
		return this.getAllById(uuids, false);
	}

	default Map<String, List<MinecraftIdentity>> findAllByName(String[] names) {
		return this.findAllByName(names, false);
	}

	default CompletableFuture<MinecraftIdentity> getByIdAsync(UUID uuid, boolean loadTexture) {
		return AsyncExecutor.supply(() -> getById(uuid, loadTexture));
	}

	default CompletableFuture<List<MinecraftIdentity>> findByNameAsync(String name, boolean loadTexture) {
		return AsyncExecutor.supply(() -> findByName(name, loadTexture));
	}

	default CompletableFuture<Map<UUID, MinecraftIdentity>> getAllByIdAsync(UUID[] uuids, boolean loadTexture) {
		return AsyncExecutor.supply(() -> getAllById(uuids, loadTexture));
	}

	default CompletableFuture<Map<String, List<MinecraftIdentity>>> findAllByNameAsync(String[] names, boolean loadTexture) {
		return AsyncExecutor.supply(() -> findAllByName(names, loadTexture));
	}

	/**
	 * Updates the identity to the backend and the cache.
	 * 
	 * @param identity the {@link MinecraftIdentity} to update
	 */
	void update(MinecraftIdentity identity);
}
