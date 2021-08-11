package eu.smashmc.api.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * A {@link Bukkit} {@link Event} that is called when a {@link Player} claims an
 * achievement.
 * 
 * @author LiquidDev
 *
 */
public class PlayerAchievementEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private AchievementEntity achievement;
	private int coins;
	private boolean cancelled;

	public PlayerAchievementEvent(Player who, AchievementEntity achievement, int coins) {
		super(who);
		this.achievement = achievement;
		this.coins = coins;
	}

	/**
	 * Returns the {@link AchievementEntity} the player is about to claim.
	 * 
	 * @return the {@link AchievementEntity}
	 */
	public AchievementEntity getAchievement() {
		return achievement;
	}

	/**
	 * Returns the amount of coins the player is rewarded with for claiming the
	 * achievement.
	 * 
	 * @return amount of coins
	 */
	public int getCoins() {
		return coins;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Set the cancellation state of the event. Canceling the event will prevent the
	 * achievement to be claimed, thus the player will not be rewarded.
	 */
	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
