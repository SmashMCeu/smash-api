package eu.smashmc.api.quest;

import java.util.UUID;

public interface QuestUpdate {
    String getSlug();
    String getDisplayName();
	String getDescription();
	UpdateType getType();
	int getCurrentAmount();
	int getRequiredAmount();
	int getXpReward();
	UUID getPlayerUuid();
}
