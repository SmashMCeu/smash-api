package eu.smashmc.api.lang;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import eu.smashmc.api.SmashMc;

/**
 * Simple {@link LanguageProvider} wrapper for {@link Bukkit} using the default
 * (Language#getDefault()) instance.
 * 
 * For BungeeCord use {@link BLang} instead.
 */
public class Lang {

	/**
	 * Initializes and sets the default {@link LanguageProvider} instance. This can
	 * only be done once.
	 * 
	 * For BungeeCord use {@link BLang#initialize(String, String)} instead.
	 * 
	 * @param scope default scope
	 */
	public static void initializeScope(String scope) {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		api.createLanguageProvider(scope);
	}

	@Deprecated
	public static void initialize(String scope, String prefix) {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		api.setDefaultProvider(api.createLanguageProvider(scope));
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