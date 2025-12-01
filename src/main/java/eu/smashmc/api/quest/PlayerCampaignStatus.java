package eu.smashmc.api.quest;

import java.util.List;

public interface PlayerCampaignStatus {

    String getCampaignName();

    ProgressionStyle getStyle();

    int getCurrentXp();
    
    boolean isCompleted();

    float getPercentDone();

    List<QuestStatus> getQuestStatusList();

}