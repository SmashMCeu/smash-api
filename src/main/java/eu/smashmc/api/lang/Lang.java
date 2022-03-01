package eu.smashmc.api.lang;

import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;

import eu.smashmc.api.SmashMc;

/**
 * 'Smart' wrapper for {@link LanguageProvider}. <br>
 * Uses the name of the calling plugin as the scope. If no provider with the
 * plugins name as scope was found, it uses the global provider instead.
 * 
 * For BungeeCord use {@link BLang} instead.
 */
public class Lang {

	/**
	 * Initializes the language system for the calling {@link Plugin}.<br>
	 * The calling plugins name will be used as the scope.<br>
	 * The chat prefix can be defined in the scopes language files with 'prefix' as
	 * the key.
	 * 
	 * For BungeeCord use {@link BLang#initialize(String, String)} instead.
	 * 
	 */
	public static void initialize() {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		api.createLanguageProvider(getScope());
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
		LanguageProvider<CommandSender> language = findProvider();
		language.sendMessage(player, translationKey, format);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey) {
		sendUnprefixedMessage(player, translationKey, (Object[]) null);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProvider();
		language.sendUnprefixedMessage(player, translationKey, format);
	}

	public static void broadcast(String translationKey) {
		broadcast(translationKey, (Object[]) null);
	}

	public static void broadcast(String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProvider();
		language.broadcast(translationKey, format);
	}

	public static String get(CommandSender player, String translationKey) {
		LanguageProvider<CommandSender> language = findProvider();
		return language.get(player, translationKey);
	}

	public static String get(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProvider();
		return language.get(player, translationKey, format);
	}

	public static String get(UUID playerUuid, String translationKey) {
		LanguageProvider<CommandSender> language = findProvider();
		return language.get(playerUuid, translationKey);
	}

	public static String get(UUID playerUuid, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProvider();
		return language.get(playerUuid, translationKey, format);
	}

	protected static LanguageProvider<CommandSender> findProvider() {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		String scope = getScope();
		if (api.existsLanguageProvider(scope)) {
			return api.getLanguageProvider(scope);
		}
		return api.getDefaultProvider();
	}

	protected static String getScope() {
		Class<?> caller = getCallingClass();
		ClassLoader classLoader = caller.getClassLoader();
		if (classLoader instanceof PluginClassLoader pluginClassLoader) {
			Plugin plugin = pluginClassLoader.getPlugin();
			String scope = plugin.getName();
			return scope;
		}
		return null;
	}

	protected static Class<?> getCallingClass() {
		try {
			final StackTraceElement[] stElements = Thread.currentThread()
					.getStackTrace();
			for (int i = 1; i < stElements.length; i++) {
				StackTraceElement ste = stElements[i];
				String className = ste.getClassName();
				if (!className.equals(Lang.class.getName()) && className.indexOf("java.lang.Thread") != 0) {
					return Class.forName(className);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Could not find calling class", e);
		}
		throw new IllegalStateException("Could not find calling class");
	}
}