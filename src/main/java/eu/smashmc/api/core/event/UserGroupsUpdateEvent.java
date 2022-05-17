package eu.smashmc.api.core.event;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;

/**
 * Similar to {@link PlayerGroupsUpdateEvent} but also called if target player
 * is offline.
 */
@Getter
public class UserGroupsUpdateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final UUID uuid;
	private final List<String> updatedGroups;

	public UserGroupsUpdateEvent(@NotNull UUID uuid, @NotNull List<String> groups) {
		this.uuid = uuid;
		this.updatedGroups = Collections.unmodifiableList(groups);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
