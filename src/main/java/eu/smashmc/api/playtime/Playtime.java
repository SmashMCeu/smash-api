package eu.smashmc.api.playtime;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = { Environment.BUKKIT, Environment.BUNGEECORD }, fallbackImpl = FallbackPlaytimeImpl.class)
public interface Playtime {

	/**
	 * Retrieve a players playtime information from their {@link UUID}.
	 * 
	 * @param uuid the players {@link UUID}
	 * @return the {@link PlaytimeInfo}
	 */
	public CompletableFuture<PlaytimeInfo> getPlaytimeInfo(UUID uuid);

	/**
	 * Retrieves the cached play time information of an online player. Might not be
	 * supported on all platforms, where it will throw an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @param onlinePlayer instance of the online player.
	 * @return the players {@link PlaytimeInfo}
	 * @throws UnsupportedOperationException if not supported on current platform
	 *                                       (e.g. BungeeCord)
	 */
	public PlaytimeInfo getPlaytimeInfo(Object onlinePlayer) throws UnsupportedOperationException;

	/**
	 * Add playtime to a player.
	 * 
	 * @param uuid     the players {@link UUID}
	 * @param playtime in milliseconds
	 */
	public void addPlaytime(UUID uuid, long playtime);

	/**
	 * On Bukkit, the checks if the play time for the given {@link UUID} is counted.
	 * In other words, if the player is online, this returns <code>true</code> if
	 * the player is actively playing.
	 * 
	 * @param uuid {@link UUID} of the player to check
	 * @return <code>true</code> if player is actively playing
	 */
	public boolean isCountingPlaytime(UUID uuid);
}
