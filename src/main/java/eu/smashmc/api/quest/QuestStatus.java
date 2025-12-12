package eu.smashmc.api.quest;

import java.time.LocalDateTime;

/**
 * Represents the status of a quest, including its progress, rewards, and timing details.
 */
public interface QuestStatus {

	/**
	 * Gets the unique identifier of the quest.
	 *
	 * @return the quest ID as a Long.
	 */
	Long getQuestId();

	/**
	 * Gets the slug (unique identifier) of the quest.
	 *
	 * @return the slug as a String.
	 */
	String getSlug();

	/**
	 * Gets the display name of the quest.
	 *
	 * @return the display name as a String.
	 */
	String getDisplayName();

	/**
	 * Gets the description of the quest.
	 *
	 * @return the description as a String.
	 */
	String getDescription();

	/**
	 * Gets the difficulty level of the quest.
	 *
	 * @return the difficulty as a {@link Difficulty}.
	 */
	Difficulty getDifficulty();

	/**
	 * Gets the current progress amount of the quest.
	 *
	 * @return the current amount as an integer.
	 */
	int getCurrentAmount();

	/**
	 * Gets the required amount to complete the quest.
	 *
	 * @return the required amount as an integer.
	 */
	int getRequiredAmount();

	/**
	 * Checks if the quest is finished.
	 *
	 * @return true if the quest is finished, false otherwise.
	 */
	boolean isFinished();

	/**
	 * Gets the type of reward for completing the quest.
	 *
	 * @return the reward type as a {@link RewardType}.
	 */
	RewardType getRewardType();

	/**
	 * Gets the value of the reward for completing the quest.
	 *
	 * @return the reward value as a String.
	 */
	String getRewardValue();

	/**
	 * Gets the description of the reward for completing the quest.
	 *
	 * @return the reward description as a String.
	 */
	String getRewardDescription();

	/**
	 * Gets the date and time when the reward was claimed.
	 *
	 * @return the reward claimed date as a {@link LocalDateTime}, or null if not claimed.
	 */
	LocalDateTime getRewardClaimedAt();

	/**
	 * Gets the experience points (XP) reward for completing the quest.
	 *
	 * @return the XP reward as an integer.
	 */
	int getXpReward();

	/**
	 * Gets the date and time when the quest becomes available.
	 *
	 * @return the available from date as a {@link LocalDateTime}.
	 */
	LocalDateTime getAvailableFrom();

	/**
	 * Gets the date and time when the quest was started.
	 *
	 * @return the started at date as a {@link LocalDateTime}.
	 */
	LocalDateTime getStartedAt();

	/**
	 * Gets the date and time when the quest was last updated.
	 *
	 * @return the last updated date as a {@link LocalDateTime}.
	 */
	LocalDateTime getLastUpdated();

}