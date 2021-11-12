package eu.smashmc.api.permission;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.SmashMc;

public class PermissionUtil {

	public static final String DEFAULT_RANK = "default";

	public static PermissionsProvider getProvider() {
		return SmashMc.getComponent(PermissionsProvider.class);
	}

	public static CustomPermissionsProvider getCustomProvider() {
		if (getProvider() instanceof FallbackPermissionProvider) {
			return new CustomPermissionsProvider(getProvider());
		}

		var permissionProvider = getProvider();
		if (permissionProvider instanceof CustomPermissionsProvider) {
			return (CustomPermissionsProvider) permissionProvider;
		} else {
			permissionProvider = new CustomPermissionsProvider(permissionProvider);
			SmashMc.registerComponent(PermissionsProvider.class, permissionProvider);
			return (CustomPermissionsProvider) permissionProvider;
		}
	}

	public static String getPrimaryGroup(UUID playerUuid) {
		return getProvider().getPrimaryGroup(playerUuid);
	}

	public static CompletableFuture<String> getPrimaryGroupOffline(UUID playerUuid) {
		return getProvider().getPrimaryGroupOffline(playerUuid);
	}
}