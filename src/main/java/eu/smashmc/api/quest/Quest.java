package eu.smashmc.api.quest;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = { Environment.BUKKIT }, fallbackImpl = FallbackQuestImpl.class)
public interface Quest {

    CompletableFuture<List<QuestUpdate>> submitEvent(SubmitEventRequest request);

    CompletableFuture<PlayerCampaignStatus> getPlayerCampaignStatus(UUID playerUuid, String campaignSlug);

}
