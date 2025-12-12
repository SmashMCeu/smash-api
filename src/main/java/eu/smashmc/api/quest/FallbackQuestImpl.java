package eu.smashmc.api.quest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class FallbackQuestImpl implements Quest {

    @Override
    public CompletableFuture<List<QuestUpdate>> submitEvent(SubmitEventRequest request) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    @Override
    public CompletableFuture<PlayerCampaignStatus> getPlayerCampaignStatus(UUID playerUuid, String campaignSlug) {
        return CompletableFuture.completedFuture(null);
    }
}
