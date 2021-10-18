package eu.smashmc.api.core.event;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import com.sun.jdi.event.Event;

import lombok.Getter;

/**
 * Bukkit {@link Event} that is called if some players gifts a role to another.
 * 
 * @author LiquidDev
 *
 */
@Getter
public class PlayerGiftRoleEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private final UUID rewardedPlayer;
	private final String rewardedRole;
	private final long duration;

	public PlayerGiftRoleEvent(@NotNull Player who, UUID rewarded, String role, long duration) {
		super(who);
		this.rewardedPlayer = rewarded;
		this.rewardedRole = role;
		this.duration = duration;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
