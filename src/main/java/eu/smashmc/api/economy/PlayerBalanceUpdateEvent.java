package eu.smashmc.api.economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import net.md_5.bungee.api.ServerPing.Players;

/**
 * {@link Bukkit} event fired when a {@link Players} balance of any currency
 * type changes for any reason.
 * 
 * @author LiquidDev
 *
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
