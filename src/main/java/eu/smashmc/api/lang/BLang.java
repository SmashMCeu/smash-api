package eu.smashmc.api.lang;

import java.util.UUID;

import eu.smashmc.api.SmashMc;
import net.md_5.bungee.api.CommandSender;

/**
 * Simple {@link LanguageProvider} wrapper for BungeeCord using the default
 * (Language#getDefault()) instance.
 * 
 * For Bukkit use {@link Lang} instead.
 */
public class BLang {

	@Deprecated
	public static void initialize(String scope, String prefix) {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		LanguageProvider<CommandSender> language = api.createLanguageProvider(scope, prefix);
		api.setDefaultProvider(language);
	}

	public static void sendMessage(CommandSender player, String translationKey) {
		sendMessage(player, translationKey, (Object[]) null);
	}

	public static void sendMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		language.sendMessage(player, translationKey, format);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey) {
		sendUnprefixedMessage(player, translationKey, (Object[]) null);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		language.sendUnprefixedMessage(player, translationKey, format);
	}

	public static void broadcast(String translationKey) {
		broadcast(translationKey, (Object[]) null);
	}

	public static void broadcast(String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		language.broadcast(translationKey, format);
	}

	public static String get(CommandSender player, String translationKey) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		return language.get(player, translationKey);
	}

	public static String get(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		return language.get(player, translationKey, format);
	}

	public static String get(UUID playerUuid, String translationKey) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		return language.get(playerUuid, translationKey);
	}

	public static String get(UUID playerUuid, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = (LanguageProvider<CommandSender>) Language.globalProvider;
		return language.get(playerUuid, translationKey, format);
	}
}