package eu.smashmc.api.economy;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Bukkit} event fired when a {@link Player}s balance of any currency
 * type changes for any reason.
 *
 * @author LiquidDev
 */
@Getter
public class PlayerBalanceUpdateEvent extends PlayerEvent {

	private Currency currency;
	private double balance;

	public PlayerBalanceUpdateEvent(@NotNull Player who, Currency currency, double balance) {
		super(who);
		this.currency = currency;
		this.balance = balance;
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
