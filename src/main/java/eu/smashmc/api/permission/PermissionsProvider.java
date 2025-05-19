package eu.smashmc.api.permission;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = {Environment.BUKKIT, Environment.BUNGEECORD}, fallbackImpl = FallbackPermissionProvider.class)
public interface PermissionsProvider {

	/**
	 * Checks if a player that may be offline has a given permission.
	 *
	 * @param playerUuid UUID of the player that may be offline
	 * @param permission the permission to check for
	 * @return CompletableFuture true if player has permission
	 */
	CompletableFuture<Boolean> hasPermission(UUID playerUuid, String permission);

	/**
	 * Gets the primary group of an online player.
	 *
	 * @param onlinePlayerUuid UUID of the player
	 * @return the name of the players primary group
	 */
	String getPrimaryGroup(UUID onlinePlayerUuid);

	/**
	 * Gets the primary group of a player, that may be offline.
	 *
	 * @param offlinePlayerUuid UUID of the player
	 * @return CompletableFuture of the name of the players primary group
	 */
	CompletableFuture<String> getPrimaryGroupOffline(UUID offlinePlayerUuid);

	/**
	 * Retrieves the groups name list.
	 *
	 * @return CompletableFuture list of group names
	 */
	CompletableFuture<List<String>> getGroups();

	/**
	 * Retrieves the groups name list of a player that might be offline.
	 *
	 * @param playerUuid UUID of a Player
	 * @return CompletableFuture list of group names
	 */
	CompletableFuture<List<String>> getGroups(UUID playerUuid);

	/**
	 * Returns the groups chat display name.
	 *
	 * @param groupName Name of the group
	 * @return the group chat prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	String getGroupChatPrefix(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the groups tab display name.
	 *
	 * @param groupName Name of the group
	 * @return the groups tab prefix
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	String getGroupTabPrefix(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the groups sort key.
	 *
	 * @param groupName Name of the group
	 * @return the groups sort key
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	String getGroupSortKey(String groupName) throws IllegalArgumentException;

	/**
	 * Returns the chat display name of an online player.
	 *
	 * @param playerUuid UUID of a Player
	 * @return the players chat prefix or empty if the player is offline
	 * @throws IllegalArgumentException when no group with the given name exists
	 */
	String getPlayerChatPrefix(UUID playerUuid);

	/**
	 * Returns the tab display name of an online player.
	 *
	 * @param playerUuid UUID of a Player
	 * @return the players tab prefix or empty if the player is offline
	 */
	String getPlayerTabPrefix(UUID playerUuid);

	/**
	 * Returns the sort key of an online player.
	 *
	 * @param playerUuid UUID of a Player
	 * @return the players sort key or empty if the player is offline
	 */
	String getPlayerSortKey(UUID playerUuid);

	/**
	 * Retrieves the highest numeric permission a player has for a given permission prefix.
	 * <p>
	 * For example, if a player has permissions like {@code build.maps.limit.3} and {@code build.maps.limit.5},
	 * calling this method with prefix {@code build.maps.limit.} will return {@code 5}.
	 * </p>
	 *
	 * @param playerUuid the UUID of the player to check permissions for
	 * @param nodePrefix the prefix of the permission node to search, ending with a dot (e.g. {@code "build.maps.limit."})
	 * @return the highest integer value found in the permission nodes with the given prefix, or {@code 0} if none found
	 */
	int getLimitPermission(UUID playerUuid, String nodePrefix);

	/**
	 * Set a Players permission
	 *
	 * @param uuid       UUID of Player
	 * @param permission Permission to set
	 * @param value      permission node value
	 * @return void
	 */
	CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value);

	/**
	 * Set a Players permission
	 *
	 * @param uuid       UUID of Player
	 * @param permission Permission to set
	 * @param value      permission node value
	 * @param server     the target server for the permission
	 * @return void
	 */
	CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value, String server);

	/**
	 * Set a group temporally for a Player
	 *
	 * @param uuid   UUID of Player
	 * @param group  Permission to set
	 * @param value  If group should be added or removed
	 * @param expiry end of the effect
	 * @return void
	 */
	CompletableFuture<Void> addGroupTemporarily(UUID uuid, String group, boolean value, Duration expiry);

	@Deprecated
	default CompletableFuture<Void> addGroupTemporarly(UUID uuid, String group, boolean value, Duration expiry) {
		return this.addGroupTemporarily(uuid, group, value, expiry);
	}

	/**
	 * Set or extend a group temporally for a Player
	 *
	 * @param uuid   UUID of Player
	 * @param group  Permission to set
	 * @param value  If group should be added or removed
	 * @param expiry end of the effect
	 * @return void
	 */
	CompletableFuture<Void> addOrExtendGroupTemporarily(UUID uuid, String group, boolean value, Duration expiry);
}