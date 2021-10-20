package eu.smashmc.api.lang;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.smashmc.api.Constants;

@SuppressWarnings("rawtypes")
class FallbackLanguageImpl extends Language {

	@Override
	public LanguageProvider createLanguageProvider(String scope, String prefix) {
		return new LanguageProvider() {

			@Override
			public String getScope() {
				return scope;
			}

			@SuppressWarnings("deprecation")
			@Override
			public void broadcast(String translationKey, Object... format) {
				Bukkit.broadcastMessage(Constants.PREFIX + translationKey + ":" + format);
			}

			@Override
			public void sendMessage(Object player, String translationKey, Object... format) {
				((Player) player).sendMessage(Constants.PREFIX + translationKey + ":" + format);
			}

			@Override
			public void sendUnprefixedMessage(Object player, String translationKey, Object... format) {
				((Player) player).sendMessage(translationKey + ":" + format);

			}

			@Override
			public String get(String countryCode, String translationKey, Object... format) throws IllegalArgumentException {
				return translationKey + ":" + format;
			}

			@Override
			public String get(UUID uuid, String translationKey, Object... format) throws IllegalArgumentException {
				return translationKey + ":" + format;
			}

			@Override
			public String get(Object player, String translationKey, Object... format) throws IllegalArgumentException {
				return translationKey + ":" + format;
			}

			@Override
			public String getFormatTranslation(String countryCode, String langStr) {
				return langStr;
			}

			@Override
			public String getCountryCode(UUID uuid) {
				return "??";
			}
		};
	}
}
