package eu.smashmc.api.lang;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * A Bukkit event that is called when a {@link Player} changed their language.
 * 
 * @author LiquidDev
 *
 */
public class PlayerChangeLanguageEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private String languageFrom;
	private String languageTo;

	public PlayerChangeLanguageEvent(Player who, String from, String to) {
		super(who);
		this.languageFrom = from;
		this.languageTo = to;
	}

	/**
	 * The two letter country code from which language the player switched from.
	 * 
	 * @return two letter country code
	 */
	public String getLanguageFrom() {
		return languageFrom;
	}

	/**
	 * The two letter country code to which language the player switched to.
	 * 
	 * @return two letter country code
	 */
	public String getLanguageTo() {
		return languageTo;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
