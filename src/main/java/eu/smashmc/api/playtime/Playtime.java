package eu.smashmc.api.playtime;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent({ Environment.BUNGEECORD })
public interface Playtime {

	/**
	 * Retrieve a players playtime information from their {@link UUID}.
	 * 
	 * @param uuid the players {@link UUID}
	 * @return the {@link PlaytimeInfo}
	 */
	public CompletableFuture<PlaytimeInfo> getPlaytimeInfo(UUID uuid);

	/**
	 * Retrieve a players playtime information by their name.
	 * 
	 * @param playername the players last seen name
	 * @return the {@link PlaytimeInfo}
	 */
	public CompletableFuture<PlaytimeInfo> getPlaytimeInfo(String playername);

	/**
	 * Add playtime to a player.
	 * 
	 * @param uuid     the players {@link UUID}
	 * @param name     the players name
	 * @param playtime in milliseconds
	 */
	public void addPlaytime(UUID uuid, String name, long playtime);
}
