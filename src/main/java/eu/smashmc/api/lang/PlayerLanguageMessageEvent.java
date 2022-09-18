package eu.smashmc.api.lang;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import lombok.Getter;

/**
 * Called when a player is messaged using the language plugin.
 * 
 * @author LiquidDev
 *
 */
@Getter
public class PlayerLanguageMessageEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private String translationKey;
	private Object[] parameters;
	/**
	 * The resulting translated message without any prefix.
	 */
	private String translatedMessage;
	/**
	 * The prefix added in front of the message or <code>null</code>.
	 */
	@Nullable
	private String prefix;

	public PlayerLanguageMessageEvent(Player who, String key, Object[] parameters, String translation, String prefix) {
		super(who);
		this.translationKey = key;
		this.parameters = parameters;
		this.translatedMessage = translation;
		this.prefix = prefix;
	}

	public boolean hasPrefix() {
		return prefix != null;
	}

	public String getTranslatedMessage(boolean withPrefix) {
		return ((withPrefix && hasPrefix()) ? prefix : "") + translatedMessage;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}