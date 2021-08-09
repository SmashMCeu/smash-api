package eu.smashmc.api.achievements;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.Set;

@SmashComponent({Environment.BUKKIT, Environment.BUNGEECORD})
public interface Achievements {

    /**
     * Retrieve all registered achievements
     *
     * @return Set of all registered {@link AchievementEntity}
     */
    Set<AchievementEntity> listAchievements();

    /**
     * Retrieve all achievement keys of a player by their uuid
     *
     * @param uuid player's uuid
     * @return Set of all registered achievement keys
     */
    Set<String> getAchievements(String uuid);

    /**
     * Claim an achievement for a player by their uuid
     *
     * @param uuid player's uuid
     * @param achievementKey key of registered achievement
     */
    void claimAchievement(String uuid, String achievementKey);

    /**
     * Register a new achievement
     *
     * @param achievementEntity {@link AchievementEntity}
     */
    void registerAchievement(AchievementEntity achievementEntity);
}
