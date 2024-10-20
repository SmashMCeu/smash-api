package eu.smashmc.api.lang;

import java.util.Locale;
import java.util.UUID;

interface LanguageProvider<T> {

	/**
	 * Return the scope prefix of this languages instance.
	 *
	 * @return scope prefix
	 */
	String getScope();

	/**
	 * Get the scopes chat prefix.
	 *
	 * @param countryCode the country code used for the translation
	 * @return chat prefix
	 */
	String getChatPrefix(String countryCode);

	/**
	 * Get the scopes chat prefix.
	 *
	 * @param uuid the users {@link UUID} to get the translation for
	 * @return chat prefix
	 */
	String getChatPrefix(UUID uuid);

	/**
	 * Get the scopes chat prefix.
	 *
	 * @param player the target player
	 * @return chat prefix
	 */
	String getChatPrefix(T player);

	/**
	 * Broadcasts the translation of the given message to all online players.
	 *
	 * @param translationKey the translation key of the message
	 * @param format         optional format
	 */
	void broadcast(String translationKey, Object... format);

	/**
	 * Send a translated message to a player. The message will have a prefix.
	 *
	 * @param player         the target player
	 * @param translationKey the translation key of the message
	 * @param format         optional format
	 */
	void sendMessage(T player, String translationKey, Object... format);

	/**
	 * Send a translated message to a player.
	 *
	 * @param player         the target player
	 * @param translationKey the translation key of the message
	 * @param format         optional format
	 */
	void sendUnprefixedMessage(T player, String translationKey, Object... format);

	/**
	 * Returns the formatted translation of a given translationKey for a given
	 * countryCode. The countryCode cannot be null.
	 *
	 * @param countryCode    the country code used for the translation
	 * @param translationKey the translation key to be used
	 * @param format         optional format string
	 * @return the translated message
	 * @throws IllegalArgumentException if countryCode is null
	 */
	String get(String countryCode, String translationKey, Object... format) throws IllegalArgumentException;

	/**
	 * Returns the formatted translation of a given translationKey for a given user
	 * by their UUID. The countryCode cannot be null.
	 *
	 * @param uuid           the users {@link UUID} to get the translation for
	 * @param translationKey the translation key to be used
	 * @param format         optional format string
	 * @return the translated message
	 * @throws IllegalArgumentException if countryCode is null
	 */
	String get(UUID uuid, String translationKey, Object... format) throws IllegalArgumentException;

	/**
	 * Returns the formatted translation of a given translationKey for a given user
	 * by their UUID. The countryCode cannot be null.
	 *
	 * @param player         the target player
	 * @param translationKey the translation key to be used
	 * @param format         optional format string
	 * @return the translated message
	 * @throws IllegalArgumentException if countryCode is null
	 */
	String get(T player, String translationKey, Object... format) throws IllegalArgumentException;

	/**
	 * Translates a string in the following format:
	 * <p>
	 * (color code)lang:(key):format1:format2:...
	 * <p>
	 * Examples: <br>
	 * - lang:chat.cooldown <br>
	 * - §clang:chat.hello:LiquidDev
	 *
	 * @param countryCode the countryCode used to translate the string
	 * @param langStr     the input String
	 * @return the translated String
	 */
	String getFormatTranslation(String countryCode, String langStr);

	/**
	 * Get the countryCode for a player by it's UUID.
	 *
	 * @param uuid the users UUID to get the country code for
	 * @return the countryCode
	 */
	String getCountryCode(UUID uuid);

	/**
	 * Get the countryCode for a player.
	 *
	 * @param player the target player
	 * @return the countryCode
	 */
	String getCountryCode(T player);


	/**
	 * Get the locale representing the players selected language by their {@link UUID}.
	 *
	 * @param uuid the users {@link UUID}
	 * @return Locale of the selected language
	 */
	Locale getLocale(UUID uuid);

	/**
	 * Get the locale representing the players selected language.
	 *
	 * @param player the target player
	 * @return Locale of the selected language
	 */
	Locale getLocale(T player);
}
