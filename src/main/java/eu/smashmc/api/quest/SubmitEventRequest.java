package eu.smashmc.api.quest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

/**
 * Represents a request to submit an event related to a quest.
 * This class contains details about the player, the quest, and additional metadata.
 */
@AllArgsConstructor
@Data
@Builder
public class SubmitEventRequest {

	/**
	 * The unique identifier of the player associated with the event.
	 */
	private UUID playerUuid;

	/**
	 * The slug (unique identifier) of the quest event.
	 */
	private String slug;

	/**
	 * A map containing additional metadata for the event, where the key is the metadata name
	 * and the value is the metadata value.
	 */
	private Map<String, String> meta;

}