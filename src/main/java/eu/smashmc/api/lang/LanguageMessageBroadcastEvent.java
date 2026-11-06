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
 * {@code translatedMessage} is the default-locale ({@code en},
 * {@code AbstractLanguageProvider.DEFAULT_COUNTRY_CODE}) fallback without the
 * prefix.
 */
@Getter
public class LanguageMessageBroadcastEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private String translationKey;
	private Object[] parameters;
	/**
	 * The message translated with the server default country code ({@code en},
	 * {@code AbstractLanguageProvider.DEFAULT_COUNTRY_CODE}), without any prefix.
	 * <p>
	 * A broadcast has no per-recipient locale, so this is only a fallback for
	 * consumers that cannot re-resolve {@code translationKey} and
	 * {@code parameters} for a specific viewer.
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
	 * @param translatedMessage the message translated with the server default
	 *                        country code ({@code en},
	 *                        {@code AbstractLanguageProvider.DEFAULT_COUNTRY_CODE}),
	 *                        without a prefix; only a fallback for consumers
	 *                        that cannot re-resolve the message for a viewer
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

	/**
	 * Returns the default-locale ({@code en}) fallback message, with or without
	 * the prefix.
	 *
	 * @param withPrefix whether to prepend the prefix if one is set
	 * @return the default-locale fallback with or without the prefix
	 */
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