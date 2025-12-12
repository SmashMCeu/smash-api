package eu.smashmc.api.quest;

import java.util.UUID;

/**
 * Represents an update related to a quest. This interface provides methods to retrieve
 * details about the quest update, such as its ID, description, type, and associated player.
 */
public interface QuestUpdate {

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
	 * Gets the type of the quest update.
	 *
	 * @return the update type as an {@link UpdateType}.
	 */
	UpdateType getType();

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
	 * Gets the experience points (XP) reward for the quest update.
	 *
	 * @return the XP reward as an integer.
	 */
	int getXpReward();

	/**
	 * Gets the unique identifier of the player associated with the quest update.
	 *
	 * @return the player's UUID as a {@link UUID}.
	 */
	UUID getPlayerUuid();
}