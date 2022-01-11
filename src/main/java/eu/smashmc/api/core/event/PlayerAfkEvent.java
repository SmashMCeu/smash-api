package eu.smashmc.api.core.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;

/**
 * {@link Bukkit} event fired when a {@link Player} changed it's AFK state.
 * 
 * @author LiquidDev
 */
@Getter
public class PlayerAfkEvent extends PlayerEvent {

	/**
	 * The new AFK state. <br>
	 * - <code>true</code> if the event was called due to the player going AFK<br>
	 * - <code>false</code> if the event was called due to the player becoming
	 * active again
	 */
	private boolean afk;

	public PlayerAfkEvent(@NotNull Player who, boolean toAfk) {
		super(who);
		this.afk = toAfk;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
