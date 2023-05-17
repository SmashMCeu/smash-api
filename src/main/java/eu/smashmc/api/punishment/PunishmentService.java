package eu.smashmc.api.punishment;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = {Environment.BUNGEECORD, Environment.BUKKIT}, fallbackImpl = PunishmentServiceFallback.class)
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
	Collection<? extends Punishment> getActivePunishments(Object player);

	/**
	 * Get users that share the same IP address with the given user.
	 *
	 * @param userUuid {@link UUID} from the reference user
	 * @return {@link Collection} of all users seen with the same IP
	 */
	CompletableFuture<? extends Collection<? extends PunishmentUser>> getUsersWithSameIp(UUID userUuid);

	/**
	 * Retrieves all active, permanent punishments with the given type and reason.
	 *
	 * @param type   Action type for the punishment, e.g. BAN
	 * @param reason Reason of the punishment, e.g. "Cheating"
	 * @param limit  Maximum punishments to be retrieved
	 * @return {@link Collection} of found {@link Punishment}s
	 */
	CompletableFuture<? extends Collection<? extends Punishment>> getPermanentPunishments(PunishmentActionType type, String reason, int limit);

	/**
	 * Temporarily adds a new ip to the VPN whitelist to let the user join.
	 *
	 * @param ip Ipv4 or ipv6
	 * @return The complete list of all currently whitelisted ips
	 */
	CompletableFuture<List<String>> addIpToVpnWhitelist(String ip);

}
