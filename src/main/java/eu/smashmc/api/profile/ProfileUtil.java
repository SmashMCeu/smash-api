package eu.smashmc.api.profile;

import eu.smashmc.api.PlayerOfflineException;
import org.bukkit.entity.Player;

import eu.smashmc.api.SmashMc;

/**
 * A simple {@link PlayerProfileService} wrapper for easy profile access.
 * 
 * @author LiquidDev
 *
 */
public class ProfileUtil {

	/**
	 * Get a players profile.
	 * 
	 * @see PlayerProfileService#getProfile(java.util.UUID)
	 * @param player the player to get the profile of
	 * @return the players {@link PlayerProfile}
	 */
	public static PlayerProfile getProfile(Player player) {
		if(!player.isOnline()){
			throw new PlayerOfflineException(player);
		}
		return SmashMc.getComponent(PlayerProfileService.class)
				.getProfile(player.getUniqueId());
	}

	/**
	 * Saves specific topics of a profile.
	 * 
	 * @param profile the {@link PlayerProfile} to update
	 * @param topics  the topics to be saved.
	 */
	public static void saveProfile(PlayerProfile profile, String... topics) {
		SmashMc.getComponent(PlayerProfileService.class)
				.updateProfile(profile, topics);
	}
}
