package eu.smashmc.api.permission;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import lombok.NoArgsConstructor;

/**
 * Used to override chat and tab prefixes in NickSystem.
 */
public class CustomPermissionsProvider implements PermissionsProvider {
	private PermissionsProvider impl;

	private HashMap<UUID, Filter> filter = new HashMap<>();

	public CustomPermissionsProvider(PermissionsProvider impl) {
		if (impl instanceof CustomPermissionsProvider) {
			throw new IllegalStateException("cannot wrap custom provider around another");
		}
		this.impl = impl;
	}

	public void resetCustom(UUID uuid) {
		filter.remove(uuid);
	}

	public void setCustomChatPrefix(UUID playerUuid, String prefix) {
		getFilter(playerUuid).chatPrefix = prefix;
	}

	public void setCustomTabPrefix(UUID playerUuid, String prefix) {
		getFilter(playerUuid).tabPrefix = prefix;
	}

	public void setCustomSortKey(UUID playerUuid, String key) {
		getFilter(playerUuid).sortKey = key;
	}

	public void setCustomGroup(UUID playerUuid, String group) {
		getFilter(playerUuid).group = group;
	}

	private Filter getFilter(UUID uuid) {
		if (filter.containsKey(uuid)) {
			return filter.get(uuid);
		}
		Filter f = new Filter();
		filter.put(uuid, f);
		return f;
	}

	public CompletableFuture<Boolean> hasPermission(UUID playerUuid, String permission) {
		return impl.hasPermission(playerUuid, permission);
	}

	public String getPrimaryGroup(UUID onlinePlayerUuid) {
		if (filter.containsKey(onlinePlayerUuid)) {
			Filter f = filter.get(onlinePlayerUuid);
			if (f.group != null) {
				return f.group;
			}
		}
		return impl.getPrimaryGroup(onlinePlayerUuid);
	}

	public CompletableFuture<String> getPrimaryGroupOffline(UUID offlinePlayerUuid) {
		if (filter.containsKey(offlinePlayerUuid)) {
			Filter f = filter.get(offlinePlayerUuid);
			if (f.group != null) {
				return CompletableFuture.completedFuture(f.group);
			}
		}
		return impl.getPrimaryGroupOffline(offlinePlayerUuid);
	}

	public CompletableFuture<List<String>> getGroups() {
		return impl.getGroups();
	}

	public CompletableFuture<List<String>> getGroups(UUID playerUuid) {
		if (filter.containsKey(playerUuid)) {
			Filter f = filter.get(playerUuid);
			if (f.group != null) {
				List<String> list = new ArrayList<String>();
				list.add(f.group);
				return impl.getGroups(playerUuid)
						.thenApply(l -> {
							list.addAll(l);
							return list;
						});
			}
		}
		return impl.getGroups(playerUuid);
	}

	public String getGroupChatPrefix(String groupName) throws IllegalArgumentException {
		return impl.getGroupChatPrefix(groupName);
	}

	public String getGroupTabPrefix(String groupName) throws IllegalArgumentException {
		return impl.getGroupTabPrefix(groupName);
	}

	public String getGroupSortKey(String groupName) throws IllegalArgumentException {
		return impl.getGroupSortKey(groupName);
	}

	public String getPlayerChatPrefix(UUID playerUuid) throws IllegalArgumentException {
		if (filter.containsKey(playerUuid)) {
			Filter f = filter.get(playerUuid);
			if (f.chatPrefix != null) {
				return f.chatPrefix;
			}
		}
		return impl.getPlayerChatPrefix(playerUuid);
	}

	public String getPlayerTabPrefix(UUID playerUuid) throws IllegalArgumentException {
		if (filter.containsKey(playerUuid)) {
			Filter f = filter.get(playerUuid);
			if (f.tabPrefix != null) {
				return f.tabPrefix;
			}
		}
		return impl.getPlayerTabPrefix(playerUuid);
	}

	public String getPlayerSortKey(UUID playerUuid) throws IllegalArgumentException {
		if (filter.containsKey(playerUuid)) {
			Filter f = filter.get(playerUuid);
			if (f.sortKey != null) {
				return f.sortKey;
			}
		}
		return impl.getPlayerSortKey(playerUuid);
	}

	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value) {
		return impl.setPermission(uuid, permission, value);
	}

	public CompletableFuture<Void> setPermission(UUID uuid, String permission, boolean value, String server) {
		return impl.setPermission(uuid, permission, value, server);
	}

	@NoArgsConstructor
	static class Filter {
		private String tabPrefix;
		private String chatPrefix;
		private String sortKey;
		private String group;
	}

	@Override
	public CompletableFuture<Void> addGroupTemporarily(UUID uuid, String group, boolean value, Duration expiry) {
		return impl.addGroupTemporarly(uuid, group, value, expiry);
	}

	@Override
	public CompletableFuture<Void> addOrExtendGroupTemporarily(UUID uuid, String group, boolean value, Duration expiry) {
		return impl.addGroupTemporarly(uuid, group, value, expiry);
	}
}
