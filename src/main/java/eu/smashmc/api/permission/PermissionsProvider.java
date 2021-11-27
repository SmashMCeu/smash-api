package eu.smashmc.api.permission;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = { Environment.BUKKIT, Environment.BUNGEECORD }, fallbackImpl = FallbackPermissionProvider.class)
public interface PermissionsProvider {

	/**
	 * Checks if a player that may be offline has a given permission.
	 * 
	 * @param playerUuid UUID of the player that may be offline
	 * @param permission the permission to check for
	 * @return CompletableFuture true if player has permission
	 */
	public CompletableFuture<Boolean> hasPermission(UUID playerUuid, String permission);

	/**
	 * Gets the primary group of an online player.
	 * 
	 * @param onlinePlayerUuid UUID of the player
	 * @return the name of the players primary group
	 */
	public String getPrimaryGroup(UUID onlinePlayerUuid);

	/**
	 * Gets the primary group of a player, that may be offline.
	 * 
	 * @param offlinePlayerUuid UUID of the player
	 * @return CompletableFuture of the name of the players primary group
	 */
	public CompletableFuture<String> getPrimaryGroupOffline(UUID offlinePlayerUuid);

	/**
	 * Retrieves the groups name list.
	 * 
	 * @return CompletableFuture list of group names
	 */
	public CompletableFuture<List<String>> getGroups();

	/**
	 * Retrieves the groups name list of a player that might be offline.
	 *
	 * @param playerUuid UUID of a Player
	 * @return CompletableFuture list of group names
	 */
	public CompletableFuture<List<String>> getGroups(UUID playerUuid);

	/**
	 * Returns the groups chat display name.
	 * 
	 * @param groupName Name of the group
	 * @return the group chat prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getGroupChatPrefix(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the groups tab display name.
	 * 
	 * @param groupName Name of the group
	 * @return the groups tab prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getGroupTabPrefix(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the groups sort key.
	 * 
	 * @param groupName Name of the group
	 * @return the groups sort key
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getGroupSortKey(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the groups chat display name.
	 * 
	 * @param playerUuid UUID of a Player
	 * @return the players chat prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getPlayerChatPrefix(UUID playerUuid) throws IllegalArgumentException;

	/**
	 * Returns the groups tab display name.
	 * 
	 * @param playerUuid UUID of a Player
	 * @return the players tab prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getPlayerTabPrefix(UUID playerUuid) throws IllegalArgumentException;

	/**
	 * Returns the groups sort key.
	 * 
	 * @param playerUuid UUID of a Player
	 * @return the players sort key
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	public String getPlayerSortKey(UUID playerUuid) throws IllegalArgumentException;

	/**
	 * Set a Players permission
	 * 
	 * @param uuid       UUID of Player
	 * @param permission Permission to set
	 * @param value      permission node value
	 * @return void
	 */
	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value);

	/**
	 * Set a Players permission
	 * 
	 * @param uuid       UUID of Player
	 * @param permission Permission to set
	 * @param value      permission node value
	 * @param server     the target server for the permission
	 * @return void
	 */
	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value, String server);

	/**
	 * Set a group temporally for a Player
	 *
	 * @param uuid   UUID of Player
	 * @param group  Permission to set
	 * @param value  If group should be added or removed
	 * @param expiry end of the effect
	 * @return void
	 */
	public CompletableFuture<Void> addGroupTemporarly(UUID uuid, String group, boolean value, Duration expiry);
}
