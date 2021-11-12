package eu.smashmc.api.permission;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class FallbackPermissionProvider implements PermissionsProvider {

	@Override
	public CompletableFuture<Boolean> hasPermission(UUID playerUuid, String permission) {
		return CompletableFuture.completedFuture(false);
	}

	@Override
	public String getPrimaryGroup(UUID onlinePlayerUuid) {
		return PermissionUtil.DEFAULT_RANK;
	}

	@Override
	public CompletableFuture<String> getPrimaryGroupOffline(UUID offlinePlayerUuid) {
		return CompletableFuture.completedFuture(PermissionUtil.DEFAULT_RANK);
	}

	@Override
	public CompletableFuture<List<String>> getGroups() {
		return CompletableFuture.completedFuture(Arrays.asList(PermissionUtil.DEFAULT_RANK));
	}

	@Override
	public CompletableFuture<List<String>> getGroups(UUID playerUuid) {
		return CompletableFuture.completedFuture(Arrays.asList(PermissionUtil.DEFAULT_RANK));
	}

	@Override
	public String getGroupChatPrefix(String groupName) throws IllegalArgumentException {
		return "§a";
	}

	@Override
	public String getGroupTabPrefix(String groupName) throws IllegalArgumentException {
		return "§a";
	}

	@Override
	public String getGroupSortKey(String groupName) throws IllegalArgumentException {
		return "a";
	}

	@Override
	public String getPlayerChatPrefix(UUID playerUuid) throws IllegalArgumentException {
		return "§a";
	}

	@Override
	public String getPlayerTabPrefix(UUID playerUuid) throws IllegalArgumentException {
		return "§a";
	}

	@Override
	public String getPlayerSortKey(UUID playerUuid) throws IllegalArgumentException {
		return "a";
	}

	@Override
	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value) {
		return CompletableFuture.completedFuture(null);
	}

	@Override
	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value, String server) {
		return CompletableFuture.completedFuture(null);
	}

	@Override
	public CompletableFuture<Void> addGroupTemporarly(UUID uuid, String group, boolean value, Duration expiry) {
		return CompletableFuture.completedFuture(null);
	}
}
