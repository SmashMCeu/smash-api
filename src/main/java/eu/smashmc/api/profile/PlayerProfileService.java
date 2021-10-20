package eu.smashmc.api.profile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Service class to retrieve and update {@link PlayerProfile}.
 * 
 * @author LiquidDev
 */
@SmashComponent(value = Environment.BUKKIT, fallbackImpl = FallbackPlayerProfileServiceImpl.class)
public interface PlayerProfileService {

	/**
	 * Get a {@link PlayerProfile} of an online player.
	 * 
	 * @param uuid {@link UUID} of the player to get the {@link PlayerProfile} for
	 * @return {@link PlayerProfile} for the player
	 * @throws IllegalStateException if the player is offline
	 */
	PlayerProfile getProfile(UUID uuid) throws IllegalStateException;

	/**
	 * Updates specific topics of a profile to the database.
	 * 
	 * @param profile the {@link PlayerProfile} to be updated
	 * @param topics  the topics to be updated
	 * @throws IllegalArgumentException if the topics are <code>null</code> or empty
	 * @throws IllegalStateException    if the profile is read-only
	 */
	void updateProfile(PlayerProfile profile, String... topics) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Retrieves a players profile without acquiring the write permission.
	 * 
	 * @param uuid the players {@link UUID}
	 * @return {@link CompletableFuture} with the {@link PlayerProfile} or
	 *         <code>null</code>
	 */
	CompletableFuture<PlayerProfile> getOfflineProfile(UUID uuid);

}
