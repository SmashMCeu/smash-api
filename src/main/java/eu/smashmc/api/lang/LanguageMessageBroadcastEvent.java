package eu.smashmc.api.lang;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;

/**
 * Called once when a Language message is broadcast to all online players
 * through {@link LanguageProvider#broadcast(String, Object...)}.
 * <p>
 * The event is fired once per broadcast, not once per recipient.
 * {@code translatedMessage} is the default-locale fallback without the prefix.
 */
@Getter
public class LanguageMessageBroadcastEvent extends Event {

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

	/**
	 * @param translationKey  the translation key broadcast to the players
	 * @param parameters      the parameters used to translate the key
	 * @param translatedMessage the message translated with the default locale,
	 *                        without a prefix
	 * @param prefix          the prefix added in front of the message or
	 *                        <code>null</code>
	 */
	public LanguageMessageBroadcastEvent(String translationKey, Object[] parameters, String translatedMessage,
			@Nullable String prefix) {
		this.translationKey = translationKey;
		this.parameters = parameters;
		this.translatedMessage = translatedMessage;
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