package eu.smashmc.api.quest;

import java.time.LocalDateTime;

public interface QuestStatus {

    Long getQuestId();

    String getSlug();
	
    String getDisplayName();
	
    String getDescription();

	Difficulty getDifficulty();

    int getCurrentAmount();

    int getRequiredAmount();

    boolean isFinished();
	
	RewardType getRewardType();
	
	String getRewardValue();
	
	String getRewardDescription();

	LocalDateTime getRewardClaimedAt();

    int getXpReward();
	
	LocalDateTime getAvailableFrom();
	
	LocalDateTime getStartedAt();
	
	LocalDateTime getLastUpdated();

}
