package eu.smashmc.api.groupsync;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = { Environment.BUKKIT, Environment.BUNGEECORD }, fallbackImpl = GroupsyncServiceFallback.class)
public interface GroupsyncService {

	/**
	 * Get all links to all platforms of a minecraft user.
	 * 
	 * @param uuid {@link UUID} of the minecraft user
	 * @return {@link Collection} of {@link PlatformUser} or an empty list
	 */
	CompletableFuture<Collection<? extends PlatformUser<?>>> getPlatformLinks(UUID uuid);

	/**
	 * Get a specific link of a minecraft user for a platform.
	 * 
	 * 
	 * @param uuid     {@link UUID} of the minecraft user
	 * @param platform platform type
	 * @return {@link PlatformUser} for the given platform or empty
	 */
	CompletableFuture<Optional<? extends PlatformUser<?>>> getPlatformLink(UUID uuid, Platform platform);

	/**
	 * Get a specific link of a minecraft user by it's platform user type.
	 * 
	 * 
	 * Eligible {@link PlatformUser} types:
	 * <ul>
	 * <li>{@link DiscordUser}</li>
	 * <li>{@link TeamspeakUser}</li>
	 * </ul>
	 * 
	 * @param uuid {@link UUID} of the minecraft user
	 * @param type Type of the {@link PlatformUser}
	 * @return {@link PlatformUser} for the given platform or empty
	 */
	<T extends PlatformUser<?>> CompletableFuture<Optional<T>> getPlatformLink(UUID uuid, Class<T> type);

}
