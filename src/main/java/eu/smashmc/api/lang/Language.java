package eu.smashmc.api.lang;

import java.util.logging.Logger;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashApi;

@SmashApi({ Environment.BUKKIT, Environment.BUNGEECORD })
public abstract class Language<T> {

	static Logger LOGGER = Logger.getLogger(LanguageProvider.class.getName());

	static LanguageProvider<?> defaultProvider;

	/**
	 * Get the default scope {@link LanguageProvider}.
	 * 
	 * @return default language
	 */
	public LanguageProvider<T> getDefaultProvider() {
		if (defaultProvider == null) {
			/* Return new language with global scope */
			/* This operation is cheap */
			return createLanguageProvider("global", "");
		}
		return (LanguageProvider<T>) defaultProvider;
	}

	/**
	 * Set the default scope {@link LanguageProvider}.
	 * 
	 * @param language default scope language
	 */
	public void setDefaultProvider(LanguageProvider<T> language) {
		/*
		 * To prevent race conditions with setting the default we only allow for one
		 * initialization of the default BukkitLanguage.
		 */
		if (defaultProvider != null) {
			throw new IllegalStateException("Default language provider already set to " + defaultProvider.getScope());
		}
		defaultProvider = language;
		LOGGER.info("Default scope is now " + language.getScope());
	}

	/**
	 * Create a new language object with a different scope
	 * 
	 * @param scope  language key prefix
	 * @param prefix the prefix for chat messages
	 * @return new {@link LanguageProvider} instance
	 */
	public abstract LanguageProvider<T> createLanguageProvider(String scope, String prefix);
}
