package eu.smashmc.api.achievement;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.List;
import java.util.UUID;

@SmashComponent({Environment.BUKKIT})
public interface Achievements {

    /**
     * Retrieve all registered achievements
     *
     * @return List of all registered {@link AchievementEntity}
     */
    List<AchievementEntity> listAchievements();

    /**
     * Retrieve all achievement keys of a player by their uuid
     *
     * @param uuid player's {@link UUID}
     * @return List of all registered achievements
     */
    List<AchievementEntity> getAchievements(UUID uuid);

    /**
     * Claim an achievement for a player by their uuid
     *
     * @param uuid player's {@link UUID}
     * @param achievementKey key of registered achievement
     */
    void claimAchievement(UUID uuid, String achievementKey);

    /**
     * Register a new achievement
     * If the achievement is already registered, it updates the registered achievement
     *
     * @param achievementEntity {@link AchievementEntity}
     */
    void registerAchievement(AchievementEntity achievementEntity);
}
