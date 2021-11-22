package eu.smashmc.api.proxysystem;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Interface for the legacy BungeeCord "ProxySystem".
 * 
 * @author LiquidDev
 *
 */
@SmashComponent(Environment.BUNGEECORD)
public interface ProxyService {

	/**
	 * Checks if the given player has streamer mode enabled. Streamer mode hides
	 * sensitive information from chat, e.g. Bans or Reports.
	 * 
	 * @param player {@link ProxiedPlayer} to be checked
	 * @return <code>true</code> if streamer mode enabled
	 */
	public boolean hasStreamerModeEnabled(ProxiedPlayer player);

}
