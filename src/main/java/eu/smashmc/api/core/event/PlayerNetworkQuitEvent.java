package eu.smashmc.api.core.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import com.sun.jdi.event.Event;

import lombok.Getter;

/**
 * Bukkit {@link Event} that is called when a player quits the network from this
 * specific server.
 * 
 * @author LiquidDev
 *
 */
@Getter
public class PlayerNetworkQuitEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	public PlayerNetworkQuitEvent(@NotNull Player who) {
		super(who);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
