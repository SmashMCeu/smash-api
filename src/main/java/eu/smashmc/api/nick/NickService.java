package eu.smashmc.api.nick;

import javax.annotation.Nullable;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * Very basic api for nick plugin.
 * 
 * @author LiquidDev
 *
 * @param <T> type of Player
 */
@SmashComponent(value = { Environment.BUKKIT })
public interface NickService<T> {

	boolean isNicked(T player);

	@Nullable
	NickInfo getNickInfo(T player);
}
