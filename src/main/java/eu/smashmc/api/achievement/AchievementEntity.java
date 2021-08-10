package eu.smashmc.api.achievement;

public interface AchievementEntity {

    /**
     * Get unique key of the achievement
     *
     * @return unique key
     */
    String getKey();

    /**
     * Get game type oft the registered achievement
     * example: Smash, Lobby
     *
     * @return game type
     */
    String getType();

    /**
     * Get the difficulty of the achievement
     *
     * @return {@link AchievementDifficulty}
     */
    AchievementDifficulty getDifficulty();

    /**
     * Get the description of the achievement
     *
     * @return description
     */
    String getDescription();
}
