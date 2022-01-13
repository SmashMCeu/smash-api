package eu.smashmc.api.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = { Environment.BUKKIT, Environment.BUNGEECORD }, fallbackImpl = FallbackLanguageImpl.class)
public abstract class Language<T> {

	static Logger LOGGER = Logger.getLogger(LanguageProvider.class.getName());

	static LanguageProvider<?> globalProvider;

	private Map<String, LanguageProvider<?>> providers = new HashMap<>();

	/**
	 * Get the default scope {@link LanguageProvider}.
	 * 
	 * @return default language
	 */
	public LanguageProvider<T> getDefaultProvider() {
		if (globalProvider == null) {
			/* Return new language with global scope */
			/* This operation is cheap */
			return createLanguageProvider("global");
		}
		return (LanguageProvider<T>) globalProvider;
	}

	/**
	 * Set the default scope {@link LanguageProvider}.
	 * 
	 * @param language default scope language
	 */
	@Deprecated
	public void setDefaultProvider(LanguageProvider<T> language) {
		globalProvider = language;
	}

	/**
	 * Create a new language object with a different scope
	 * 
	 * @param scope language key prefix
	 * @throws IllegalStateException if a language provider with the given scope
	 *                               already exists.
	 * @return new {@link LanguageProvider} instance
	 */
	public LanguageProvider<T> createLanguageProvider(String scope) throws IllegalStateException {
		if (existsLanguageProvider(scope)) {
			throw new IllegalStateException("Language provider with scope '" + scope + "' already exists.");
		}
		final String lowerScope = scope.toLowerCase();
		var provider = this.constructProvider(lowerScope);
		providers.put(lowerScope, provider);
		return provider;
	}

	public LanguageProvider<T> getLanguageProvider(String scope) throws IllegalStateException {
		final String lowerScope = scope.toLowerCase();
		var provider = this.providers.get(lowerScope);
		if (provider == null) {
			throw new IllegalStateException("No language provider with scope '" + scope + "' found.");
		}
		return (LanguageProvider<T>) provider;
	}

	public boolean existsLanguageProvider(String scope) {
		if (scope == null) {
			return false;
		}
		return this.providers.get(scope.toLowerCase()) != null;
	}

	@Deprecated
	public LanguageProvider<T> createLanguageProvider(String scope, String prefix) {
		return this.createLanguageProvider(scope);
	}

	protected abstract LanguageProvider<T> constructProvider(String scope);

}
