package eu.smashmc.api.groupsync;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class GroupsyncServiceFallback implements GroupsyncService {

	@Override
	public CompletableFuture<Collection<? extends PlatformUser<?>>> getPlatformLinks(UUID uuid) {
		return CompletableFuture.completedFuture(Collections.emptyList());
	}

	@Override
	public CompletableFuture<Optional<? extends PlatformUser<?>>> getPlatformLink(UUID uuid, Platform platform) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public <T extends PlatformUser<?>> CompletableFuture<Optional<T>> getPlatformLink(UUID uuid, Class<T> type) {
		return CompletableFuture.completedFuture(Optional.empty());
	}
}
