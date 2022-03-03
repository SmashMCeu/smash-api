package eu.smashmc.api.punishment;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import net.md_5.bungee.api.connection.ProxiedPlayer;

class PunishmentServiceFallback implements PunishmentService {

	@Override
	public CompletableFuture<Optional<PunishmentUser>> getUser(UUID uuid) {
		return CompletableFuture.completedFuture(Optional.empty());
	}

	@Override
	public Collection<Punishment> getActivePunishments(ProxiedPlayer player) {
		return Collections.emptyList();
	}

	@Override
	public CompletableFuture<Collection<? extends PunishmentUser>> getUsersWithSameIp(UUID userUuid) {
		return CompletableFuture.completedFuture(Collections.emptyList());
	}
}
