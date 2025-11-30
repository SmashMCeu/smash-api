package eu.smashmc.api.quest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class SubmitEventRequest {

	private UUID playerUuid;
	private String slug;
	private Map<String, String> meta;
	
}