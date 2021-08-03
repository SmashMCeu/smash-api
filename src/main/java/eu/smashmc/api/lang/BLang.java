package eu.smashmc.api.lang;

import java.util.UUID;

import eu.smashmc.api.SmashMc;
import net.md_5.bungee.api.CommandSender;

/**
 * Simple wrapper for {@link LanguageProvider} using the default
 * (Language#getDefault()) instance.
 */
public class BLang {

	/**
	 * Initializes and sets the default {@link LanguageProvider} instance. This can
	 * only be done once.
	 * 
	 * @param scope  default scope
	 * @param prefix default prefix
	 */
	public static void initialize(String scope, String prefix) {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		LanguageProvider<CommandSender> language = api.createLanguageProvider(scope, prefix);
		api.setDefaultProvider(language);
	}

	public static void sendMessage(CommandSender player, String translationKey) {
		sendMessage(player, translationKey, (Object[]) null);
	}

	public static void sendMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		language.sendMessage(player, translationKey, format);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey) {
		sendUnprefixedMessage(player, translationKey, (Object[]) null);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		language.sendUnprefixedMessage(player, translationKey, format);
	}

	public static void broadcast(String translationKey) {
		broadcast(translationKey, (Object[]) null);
	}

	public static void broadcast(String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		language.broadcast(translationKey, format);
	}

	public static String get(CommandSender player, String translationKey) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		return language.get(player, translationKey);
	}

	public static String get(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		return language.get(player, translationKey, format);
	}

	public static String get(UUID playerUuid, String translationKey) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		return language.get(playerUuid, translationKey);
	}

	public static String get(UUID playerUuid, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.defaultProvider;
		return language.get(playerUuid, translationKey, format);
	}
}