package eu.smashmc.api.punishment;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SmashComponent(value = Environment.BUNGEECORD, fallbackImpl = PunishmentServiceFallback.class)
public interface PunishmentService {

	/**
	 * Get all known punishment information about a user by their {@link UUID}. The
	 * returned data might just be a snapshot and not updated after retrieving it.
	 * 
	 * @param uuid {@link UUID} of the user
	 * @return {@link CompletableFuture} {@link Optional} {@link PunishmentUser}
	 */
	CompletableFuture<? extends Optional<? extends PunishmentUser>> getUser(UUID uuid);

	/**
	 * Get all active punishments of an online player.
	 * 
	 * @param player online player
	 * @return {@link Collection} active punishments
	 */
	Collection<? extends Punishment> getActivePunishments(ProxiedPlayer player);

}
