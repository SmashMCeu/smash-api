package eu.smashmc.api.lang;

import java.util.logging.Logger;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashApi;

@SmashApi({ Environment.BUKKIT, Environment.BUNGEECORD })
public abstract class LanguageApi<T> {

	static Logger LOGGER = Logger.getLogger(Language.class.getName());

	static Language<?> defaultLanguage;

	/**
	 * Get the default scope {@link Language}.
	 * 
	 * @return default language
	 */
	public Language<T> getDefault() {
		if (defaultLanguage == null) {
			/* Return new language with global scope */
			/* This operation is cheap */
			return createLanguage("global", "");
		}
		return (Language<T>) defaultLanguage;
	}

	/**
	 * Set the default scope {@link Language}.
	 * 
	 * @param language default scope language
	 */
	public void setDefault(Language<T> language) {
		/*
		 * To prevent race conditions with setting the default we only allow for one
		 * initialization of the default BukkitLanguage.
		 */
		if (defaultLanguage != null) {
			throw new IllegalStateException("Default language scope already set to " + defaultLanguage.getScope());
		}
		defaultLanguage = language;
		LOGGER.info("Default scope is now " + language.getScope());
	}

	/**
	 * Create a new language object with a different scope
	 * 
	 * @param scope  language key prefix
	 * @param prefix the prefix for chat messages
	 * @return new {@link Language} instance
	 */
	public abstract Language<T> createLanguage(String scope, String prefix);
}
