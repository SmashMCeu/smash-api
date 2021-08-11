package eu.smashmc.api.achievement;

import java.util.List;
import java.util.UUID;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent({ Environment.BUKKIT })
public interface Achievements {

	/**
	 * Retrieve all registered achievements.
	 *
	 * @return {@link List} of all registered {@link AchievementEntity}
	 */
	List<AchievementEntity> listAchievements();

	/**
	 * Retrieve all achievement keys of a player by their {@link UUID}
	 *
	 * @param uuid player's {@link UUID}
	 * @return List of all registered achievements
	 */
	List<AchievementEntity> getAchievements(UUID uuid);

	/**
	 * Claim an achievement for a player by their {@link UUID}. <br>
	 * If the achievement is already claimed by the player, nothing happens. It
	 * throws an {@link IllegalArgumentException} if the achievement doesn't exist.
	 *
	 * @param uuid           player's {@link UUID}
	 * @param achievementKey key of the achievement
	 * @throws IllegalArgumentException if the given achievement key does not exist
	 */
	void claimAchievement(UUID uuid, String achievementKey) throws IllegalArgumentException;

	/**
	 * Register a new achievement. If the achievement is already registered, it
	 * updates the registered achievement.
	 *
	 * @param achievementEntity {@link AchievementEntity}
	 */
	void registerAchievement(AchievementEntity achievementEntity);
}
