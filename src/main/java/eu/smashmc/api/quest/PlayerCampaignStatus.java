package eu.smashmc.api.quest;

import java.util.List;

/**
 * Represents the status of a player's campaign in a quest system.
 */
public interface PlayerCampaignStatus {

	/**
	 * Gets the name of the campaign.
	 *
	 * @return the name of the campaign as a String.
	 */
	String getCampaignName();

	/**
	 * Gets the progression style of the campaign.
	 *
	 * @return the progression style as a {@link ProgressionStyle}.
	 */
	ProgressionStyle getStyle();

	/**
	 * Gets the current experience points (XP) of the player in the campaign.
	 *
	 * @return the current XP as an integer.
	 */
	int getCurrentXp();

	/**
	 * Checks if the campaign is completed.
	 *
	 * @return true if the campaign is completed, false otherwise.
	 */
	boolean isCompleted();

	/**
	 * Gets the percentage of the campaign completed.
	 *
	 * @return the percentage completed as a float.
	 */
	float getPercentDone();

	/**
	 * Gets the list of quest statuses associated with the campaign.
	 *
	 * @return a list of {@link QuestStatus} objects.
	 */
	List<QuestStatus> getQuestStatusList();

}