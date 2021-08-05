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

	/**
	 * Retrieves the identity of a user by their {@link UUID}. When setting
	 * loadTexture to true, the lookup might bypass some caches and take longer.
	 * 
	 * @param uuid
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return the {@link MinecraftIdentity} or null
	 */
	@Nullable
	MinecraftIdentity getById(UUID uuid, boolean loadTexture);

	/**
	 * Searches the identities of users with the given name. When setting
	 * loadTexture to true, the lookup might bypass some caches and take longer.
	 * 
	 * @param name
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return a {@link List} of {@link MinecraftIdentity}, may be empty if none
	 *         found
	 */
	List<MinecraftIdentity> findByName(String name, boolean loadTexture);

	/**
	 * Retrieves all identities by their {@link UUID} in the given array.. When
	 * setting loadTexture to true, the lookup might bypass some caches and take
	 * longer.
	 * 
	 * @param uuids       array of {@link UUID}
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return the {@link MinecraftIdentity} or null
	 */
	Map<UUID, MinecraftIdentity> getAllById(UUID[] uuids, boolean loadTexture);

	/**
	 * Searches all identities of all users with any name in the given array. When
	 * setting loadTexture to true, the lookup might bypass some caches and take
	 * longer.
	 * 
	 * @param names       list of names to search for
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return names mapped to a {@link List} of {@link MinecraftIdentity}, may be
	 *         empty if none found
	 */
	Map<String, List<MinecraftIdentity>> findAllByName(String[] names, boolean loadTexture);

	/**
	 * @see MinecraftIdentityProvider#getById(UUID, boolean) - loadTexture defaults
	 *      to false.
	 */
	@Nullable
	@Override
	default MinecraftIdentity getById(UUID uuid) {
		return this.getById(uuid, false);
	}

	/**
	 * @see MinecraftIdentityProvider#findByName(String, boolean) - loadTexture
	 *      defaults to false.
	 */
	@Override
	default List<MinecraftIdentity> findByName(String name) {
		return findByName(name, false);
	}

	/**
	 * @see MinecraftIdentityProvider#getAllById(UUID[], boolean) - loadTexture
	 *      defaults to false.
	 */
	@Override
	default Map<UUID, MinecraftIdentity> getAllById(UUID[] uuids) {
		return this.getAllById(uuids, false);
	}

	/**
	 * @see MinecraftIdentityProvider#findAllByName(String[], boolean) - loadTexture
	 *      defaults to false.
	 */
	default Map<String, List<MinecraftIdentity>> findAllByName(String[] names) {
		return this.findAllByName(names, false);
	}

	/**
	 * Retrieves the identity of a user by their {@link UUID} as a non-blocking
	 * operation. When setting loadTexture to true, the lookup might bypass some
	 * caches and take longer.
	 * 
	 * @param uuid
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return {@link CompletableFuture} with the {@link MinecraftIdentity} or null
	 */
	default CompletableFuture<MinecraftIdentity> getByIdAsync(UUID uuid, boolean loadTexture) {
		return AsyncExecutor.supply(() -> getById(uuid, loadTexture));
	}

	/**
	 * Searches the identities of users with the given name as a non-blocking
	 * operation. When setting loadTexture to true, the lookup might bypass some
	 * caches and take longer.
	 * 
	 * @param name
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return {@link CompletableFuture} with {@link List} of
	 *         {@link MinecraftIdentity}, may be empty if none found
	 */
	default CompletableFuture<List<MinecraftIdentity>> findByNameAsync(String name, boolean loadTexture) {
		return AsyncExecutor.supply(() -> findByName(name, loadTexture));
	}

	/**
	 * Retrieves all identities by their {@link UUID} in the given array as a
	 * non-blocking operation. When setting loadTexture to true, the lookup might
	 * bypass some caches and take longer.
	 * 
	 * @param uuids       array of {@link UUID}
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return {@link CompletableFuture} with the {@link MinecraftIdentity} or null
	 */
	default CompletableFuture<Map<UUID, MinecraftIdentity>> getAllByIdAsync(UUID[] uuids, boolean loadTexture) {
		return AsyncExecutor.supply(() -> getAllById(uuids, loadTexture));
	}

	/**
	 * Searches all identities of all users with any name in the given array as a
	 * non-blocking operation. When setting loadTexture to true, the lookup might
	 * bypass some caches and take longer.
	 * 
	 * @param names       list of names to search for
	 * @param loadTexture specify whether or not the textures of the identity should
	 *                    be loaded as well.
	 * @return {@link CompletableFuture} with the names mapped to a {@link List} of
	 *         {@link MinecraftIdentity}, may be empty if none found
	 */
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
