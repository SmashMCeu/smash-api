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
		return CompletableFuture.completedFuture(new PlayerCampaignStatus() {

			@Override
			public String getCampaignName() {
				return campaignSlug;
			}

			@Override
			public ProgressionStyle getStyle() {
				return ProgressionStyle.CHECKLIST;
			}

			@Override
			public int getCurrentXp() {
				return 0;
			}

			@Override
			public boolean isCompleted() {
				return false;
			}

			@Override
			public float getPercentDone() {
				return 0;
			}

			@Override
			public List<QuestStatus> getQuestStatusList() {
				return Collections.emptyList();
			}
		});
	}
}
