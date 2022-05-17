package eu.smashmc.api.core.event;

import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;

/**
 * Called when the groups of a player were updated.
 */
@Getter
public class PlayerGroupsUpdateEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private final List<String> updatedGroups;

	public PlayerGroupsUpdateEvent(@NotNull Player who, @NotNull List<String> groups) {
		super(who);
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
