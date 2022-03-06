package eu.smashmc.api.nick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import eu.smashmc.api.identity.minecraft.MinecraftIdentity;
import lombok.Getter;

/**
 * {@link Bukkit} event fired when a Player nicks or unnicks.
 * 
 * @author LiquidDev
 *
 */
@Getter
public class PlayerNickEvent extends PlayerEvent {

	/**
	 * <code>true</code> if the player goes from unnicked to nicked.
	 */
	final boolean toNickedState;
	/**
	 * The real players identity.
	 */
	final MinecraftIdentity realIdentity;
	/**
	 * The players identity as a nicked player.
	 */
	final MinecraftIdentity nickIdentity;

	public PlayerNickEvent(@NotNull Player who, MinecraftIdentity originalIdentity, MinecraftIdentity nickedIdentity, boolean toNickedState) {
		super(who);
		this.realIdentity = originalIdentity;
		this.nickIdentity = nickedIdentity;
		this.toNickedState = toNickedState;
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
