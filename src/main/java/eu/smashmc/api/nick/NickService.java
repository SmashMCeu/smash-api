package eu.smashmc.api.nick;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Very basic api for nick plugin.
 *
 * @param <T> type of Player
 * @author LiquidDev
 */
@SmashComponent(value = {Environment.BUKKIT}, fallbackImpl = NickServiceFallback.class)
public interface NickService<T> {

	boolean isNicked(T player);

	Optional<? extends NickInfo> getNick(T player);

	@Nullable
	@Deprecated
	default NickInfo getNickInfo(T player) {
		return getNick(player).orElse(null);
	}
}
