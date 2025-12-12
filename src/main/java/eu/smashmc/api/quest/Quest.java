package eu.smashmc.api.quest;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a quest in the system. This interface provides methods to handle quest-related operations.
 */
@SmashComponent(value = { Environment.BUKKIT }, fallbackImpl = FallbackQuestImpl.class)
public interface Quest {

	/**
	 * Submits an event related to the quest and processes it asynchronously.
	 *
	 * @param request the event submission request containing event details.
	 * @return a {@link CompletableFuture} that resolves to a list of {@link QuestUpdate} objects representing the updates
	 *         resulting from the submitted event.
	 */
	CompletableFuture<List<QuestUpdate>> submitEvent(SubmitEventRequest request);

	/**
	 * Retrieves the campaign status of a player for a specific campaign asynchronously.
	 *
	 * @param playerUuid   the unique identifier of the player.
	 * @param campaignSlug the slug (identifier) of the campaign.
	 * @return a {@link CompletableFuture} that resolves to the {@link PlayerCampaignStatus} of the player for the specified campaign.
	 */
	CompletableFuture<PlayerCampaignStatus> getPlayerCampaignStatus(UUID playerUuid, String campaignSlug);

}