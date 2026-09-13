package eu.smashmc.api.profile;

import com.google.common.base.Suppliers;
import eu.smashmc.api.PlayerOfflineException;
import eu.smashmc.api.SmashMc;
import org.bukkit.entity.Player;

import java.util.function.Supplier;

/**
 * A simple {@link PlayerProfileService} wrapper for easy profile access.
 *
 * @author LiquidDev
 *
 */
public class ProfileUtil {

	private static final Supplier<PlayerProfileService> PROFILE_SERVICE_SUPPLIER = Suppliers.memoize(() -> SmashMc.getComponent(PlayerProfileService.class));

	/**
	 * Get a players profile.
	 *
	 * @param player the player to get the profile of
	 * @return the players {@link PlayerProfile}
	 * @see PlayerProfileService#getProfile(java.util.UUID)
	 */
	public static PlayerProfile getProfile(Player player) {
		if (!player.isOnline()) {
			throw new PlayerOfflineException(player);
		}
		return PROFILE_SERVICE_SUPPLIER.get().getProfile(player.getUniqueId());
	}

	/**
	 * Saves specific topics of a profile.
	 *
	 * @param profile the {@link PlayerProfile} to update
	 * @param topics  the topics to be saved.
	 */
	public static void saveProfile(PlayerProfile profile, String... topics) {
		PROFILE_SERVICE_SUPPLIER.get().updateProfile(profile, topics);
	}
}
