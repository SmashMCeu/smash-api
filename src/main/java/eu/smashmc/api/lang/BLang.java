package eu.smashmc.api.lang;

import java.util.UUID;

import eu.smashmc.api.SmashMc;
import net.md_5.bungee.api.CommandSender;

/**
 * Simple wrapper for {@link Language} using the default (Language#getDefault())
 * instance.
 */
public class BLang {

	/**
	 * Initializes and sets the default {@link Language} instance. This can only be
	 * done once.
	 * 
	 * @param scope  default scope
	 * @param prefix default prefix
	 */
	public static void initialize(String scope, String prefix) {
		LanguageApi<CommandSender> api = SmashMc.getApi(LanguageApi.class);
		Language<CommandSender> language = api.createLanguage(scope, prefix);
		api.setDefault(language);
	}

	public static void sendMessage(CommandSender player, String translationKey) {
		sendMessage(player, translationKey, (Object[]) null);
	}

	public static void sendMessage(CommandSender player, String translationKey, Object... format) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		language.sendMessage(player, translationKey, format);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey) {
		sendUnprefixedMessage(player, translationKey, (Object[]) null);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey, Object... format) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		language.sendUnprefixedMessage(player, translationKey, format);
	}

	public static void broadcast(String translationKey) {
		broadcast(translationKey, (Object[]) null);
	}

	public static void broadcast(String translationKey, Object... format) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		language.broadcast(translationKey, format);
	}

	public static String get(CommandSender player, String translationKey) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		return language.get(player, translationKey);
	}

	public static String get(CommandSender player, String translationKey, Object... format) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		return language.get(player, translationKey, format);
	}

	public static String get(UUID playerUuid, String translationKey) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		return language.get(playerUuid, translationKey);
	}

	public static String get(UUID playerUuid, String translationKey, Object... format) {
		Language<CommandSender> language = (Language<CommandSender>) LanguageApi.defaultLanguage;
		return language.get(playerUuid, translationKey, format);
	}
}