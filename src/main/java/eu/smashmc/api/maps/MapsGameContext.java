package eu.smashmc.api.maps;

import eu.smashmc.api.lang.LanguageProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.command.CommandSender;

import javax.annotation.Nullable;
import java.util.Set;

@Getter
@Builder
public class MapsGameContext {

	/* Required parameters */
	@NonNull
	private final String gameType;

	@NonNull
	private final LanguageProvider<CommandSender> languageProvider;

	/* Optional parameters */
	@Nullable
	private final Set<String> tags;

	private boolean requireApproved;

}
