package eu.smashmc.api.identity.minecraft;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.concurrent.AsyncExecutor;
import eu.smashmc.api.identity.IdentityProvider;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

/**
 * A {@link UUID} to name, name to {@link UUID} and skin provider for Minecraft
 * players.
 * 
 * @author LiquidDev
 */
@SmashComponent
public interface MinecraftIdentityProvider extends IdentityProvider<UUID, MinecraftIdentity> {

	/**
	 * Retrieves the name of a user by their UUID. Returns <code>null</code> if the
	 * user was not found. This is a blocking operation that may perform networking
	 * and database IO on the calling thread.
	 * 
	 * @param uuid the identifier of the user
	 * @return the users name or <code>null</code>
	 */
	@Nullable
	String getName(UUID uuid);

	/**
	 * Retrieves the UUID of the most likely user with the given name. Returns
	 * <code>null</code> if the user was not found. This is a blocking operation
	 * that may perform networking and database IO on the calling thread.
	 * 
	 * @param name of the user
	 * @return {@link UUID} of the user or <code>null</code>
	 */
	@Nullable
	UUID getUuid(String name);

	/**
	 * Retrieves a players Skin/Texture by their {@link UUID}. Returns
	 * <code>null</code> if the user was not found. This is a blocking operation
	 * that may perform networking and database IO on the calling thread.
	 * 
	 * @param uuid the {@link UUID} of the user
	 * @return {@link CompletableFuture} containing the {@link TexturesProperty} of
	 *         the player or <code>null</code>
	 */
	@Nullable
	TexturesProperty getTextures(UUID uuid);

	/**
	 * Retrieves the UUID of a user by their name as a non-blocking
	 * {@link CompletableFuture}. The result of the {@link CompletableFuture} will
	 * be <code>null</code> if the user was not found.
	 * 
	 * @param uuid the identifier of the user
	 * @return {@link CompletableFuture} containing the name of the user or
	 *         <code>null</code>
	 */
	default CompletableFuture<String> getNameAsync(UUID uuid) {
		return AsyncExecutor.supply(() -> this.getName(uuid));
	}

	/**
	 * Retrieves the UUID of the most likely user with the given name as a
	 * non-blocking {@link CompletableFuture}. The result of the
	 * {@link CompletableFuture} will be <code>null</code> if the user was not
	 * found.
	 * 
	 * @param name of the user
	 * @return {@link CompletableFuture} containing the {@link UUID} of the user or
	 *         <code>null</code>
	 */
	default CompletableFuture<UUID> getUuidAsync(String name) {
		return AsyncExecutor.supply(() -> this.getUuid(name));
	}

	/**
	 * Retrieves a players Skin/Texture as a non-blocking {@link CompletableFuture}.
	 * The result of the {@link CompletableFuture} will be <code>null</code> if the
	 * player was not found.
	 * 
	 * @param uuid the {@link UUID} of the player
	 * @return {@link CompletableFuture} containing the {@link TexturesProperty} of
	 *         the player or <code>null</code>
	 */
	default CompletableFuture<TexturesProperty> getTexturesAsync(UUID uuid) {
		return AsyncExecutor.supply(() -> this.getTextures(uuid));
	}

	/**
	 * Updates the identity to the backend and in the cache.
	 * 
	 * @param identity the {@link MinecraftIdentity} to update
	 */
	void updateIdentity(MinecraftIdentity identity);
}
