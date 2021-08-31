package eu.smashmc.api.tos;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

/**
 * API to check if a player has accepted the TOS on the lobby.
 * 
 * @author LiquidDev
 *
 */
@SmashComponent(Environment.BUKKIT)
public interface LobbyTos {

	/**
	 * Checks if a user has accepted the TOS.
	 * 
	 * @param player the player to check for TOS
	 * @return <code>true</code> if they accepted the TOS
	 */
	public boolean hasAcceptedTos(Player player);

	/**
	 * Retrieves information about a player that accepted the TOS. Returns
	 * <code>null</code> if the players has not yet accepted the TOS.
	 * 
	 * @param player the player to retrieve information about
	 * @return {@link AcceptedTos} information or <code>null</code>
	 */
	@Nullable
	public AcceptedTos getAcceptedTos(Player player);

}
