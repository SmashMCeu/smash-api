package eu.smashmc.api.achievement;

import java.util.List;
import java.util.UUID;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = { Environment.BUKKIT }, fallbackImpl = FallbackAchievementsImpl.class)
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
	 * Checks if a player has earned an achievement. Also returns false if the
	 * achievement does not exist.
	 * 
	 * @param uuid           the player's {@link UUID}
	 * @param achievementKey key of the achievement
	 * @return <code>true</code> if achievement is claimed, <code>false</code> if
	 *         not or achievement does not exist
	 */
	boolean hasAchievement(UUID uuid, String achievementKey);

	/**
	 * Register a new achievement. If the achievement is already registered, it
	 * updates the registered achievement.
	 *
	 * @param achievementEntity {@link AchievementEntity}
	 */
	void registerAchievement(AchievementEntity achievementEntity);

	/**
	 * Checks if a player has all known achievements.
	 * 
	 * @param uuid {@link UUID} of the player
	 * @return <code>true</code> if player has all achievements
	 */
	boolean hasAllAchievements(UUID uuid);
}
