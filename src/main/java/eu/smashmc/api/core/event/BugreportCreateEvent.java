package eu.smashmc.api.core.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Called when a player creates a bug report.
 * This event holds metadata and additional information related to the report.
 */
public class BugreportCreateEvent extends PlayerEvent {

	private final List<String> additionalInfo = new ArrayList<>();
	private final List<BugReportMetadata> metadata = new ArrayList<>();

	private static final HandlerList handlers = new HandlerList();

	public BugreportCreateEvent(@NotNull Player reportedBy) {
		super(reportedBy);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Adds a single line of additional information to the bug report.
	 *
	 * @param info a descriptive string (e.g., steps to reproduce, environment notes)
	 */
	public void addAdditionalInfo(@NotNull String info) {
		this.additionalInfo.add(info);
	}

	public List<String> getAdditionalInfo() {
		return Collections.unmodifiableList(additionalInfo);
	}

	/**
	 * Adds a metadata key-value pair to the bug report.
	 *
	 * @param key   the metadata key (e.g., "GameState")
	 * @param value the metadata value (e.g., "Ingame")
	 */
	public void addMetadata(@NotNull String key, @NotNull Object value) {
		this.metadata.add(new BugReportMetadata(key, String.valueOf(value)));
	}

	public List<BugReportMetadata> getMetadata() {
		return Collections.unmodifiableList(metadata);
	}

	@Getter
	public static class BugReportMetadata {
		private final String key;
		private final String value;

		public BugReportMetadata(@NotNull String key, @NotNull String value) {
			this.key = key;
			this.value = value;
		}
	}
}