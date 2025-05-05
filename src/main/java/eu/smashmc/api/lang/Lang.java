package eu.smashmc.api.lang;

import eu.smashmc.api.SmashMc;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;

import java.util.Locale;
import java.util.UUID;

/**
 * 'Smart' wrapper for {@link LanguageProvider}. <br>
 * Uses the name of the calling plugin as the scope. If no provider with the
 * plugins name as scope was found, it uses the global provider instead.
 * <p>
 * For BungeeCord use {@link BLang} instead.
 */
public class Lang {

	/**
	 * Initializes the language system for the calling {@link Plugin}.<br>
	 * The calling plugins name will be used as the scope.<br>
	 * The chat prefix can be defined in the scopes language files with 'prefix' as
	 * the key.
	 */
	public static void initialize() {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		api.createLanguageProvider(getScope());
	}

	/**
	 * Removes the language provider for the given scope. Might also unload all relates keys from the language registry.
	 *
	 * @param scope the scope to be terminated.
	 * @throws IllegalStateException when there was no provider to the given scope
	 */
	public static void remove(String scope) throws IllegalStateException {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		api.removeLanguageProvider(getScope());
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
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		language.sendMessage(player, translationKey, format);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey) {
		sendUnprefixedMessage(player, translationKey, (Object[]) null);
	}

	public static void sendUnprefixedMessage(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		language.sendUnprefixedMessage(player, translationKey, format);
	}

	public static void broadcast(String translationKey) {
		broadcast(translationKey, (Object[]) null);
	}

	public static void broadcast(String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		language.broadcast(translationKey, format);
	}

	public static String get(CommandSender player, String translationKey) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.get(player, translationKey);
	}

	public static String get(CommandSender player, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.get(player, translationKey, format);
	}

	public static String get(UUID playerUuid, String translationKey) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.get(playerUuid, translationKey);
	}

	public static String get(UUID playerUuid, String translationKey, Object... format) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.get(playerUuid, translationKey, format);
	}

	public static Locale getLocale(CommandSender player) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.getLocale(player);
	}

	public static Locale getLocale(UUID playerUuid) {
		LanguageProvider<CommandSender> language = findProviderFromCallingClass();
		return language.getLocale(playerUuid);
	}

	public static LanguageProvider<CommandSender> findProviderFromCallingClass() {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);
		String scope = getScope();
		if (api.existsLanguageProvider(scope)) {
			return api.getLanguageProvider(scope);
		}
		return api.getDefaultProvider();
	}

	protected static String getScope() {
		Language<CommandSender> api = SmashMc.getComponent(Language.class);

		// first, try to find via package name (faster)
		String packageName = getCallingPackageName();
		if (packageName.startsWith("eu.smashmc")) {
			String scopeName = packageName.split("\\.")[2];
			if (api.existsLanguageProvider(scopeName)) {
				return scopeName;
			}
		}

		// falling back to calling class plugin name
		return getScopeViaCallingPlugin();
	}

	protected static String getCallingPackageName() {
		final StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			String className = ste.getClassName();
			if (!className.equals(Lang.class.getName()) && className.indexOf("java.lang.Thread") != 0) {
				return className;
			}
		}
		return "";
	}

	protected static String getScopeViaCallingPlugin() {
		Class<?> caller = getCallingClass();
		ClassLoader classLoader = caller.getClassLoader();
		if (classLoader instanceof PluginClassLoader pluginClassLoader) {
			Plugin plugin = pluginClassLoader.getPlugin();
			if (plugin != null) {
				return plugin.getName().toLowerCase().trim();
			}
		}
		return null;
	}

	protected static Class<?> getCallingClass() {
		try {
			ClassLoader classLoader = Lang.class.getClassLoader();
			final StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
			for (int i = 1; i < stElements.length; i++) {
				StackTraceElement ste = stElements[i];
				String className = ste.getClassName();
				if (!className.equals(Lang.class.getName()) && className.indexOf("java.lang.Thread") != 0) {
					return Class.forName(className, false, classLoader);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Could not find calling class", e);
		}
		throw new IllegalStateException("Could not find calling class");
	}
}