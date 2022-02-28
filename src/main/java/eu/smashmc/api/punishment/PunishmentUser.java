package eu.smashmc.api.punishment;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PunishmentUser {

	/**
	 * Get the {@link UUID} belonging to the user.
	 * 
	 * @return {@link UUID}
	 */
	UUID getUuid();

	/**
	 * Get all punishments ever created for this user.
	 * 
	 * @return all punishments
	 */
	Collection<? extends Punishment> getPunishments();

	/**
	 * Get all currently active punishments.
	 * 
	 * @return all active punishments
	 */
	Collection<? extends Punishment> getActivePunishments();

	/**
	 * Get last known IP address of the user.
	 * 
	 * @return last known IP address.
	 */
	String getLastIp();

	/**
	 * IP address history of the user.
	 * 
	 * @return {@link List} of some IP addresses the user was seem with
	 */
	List<String> getIpHistory();

	/**
	 * Violation points of the user. The key is the name of the reason and values
	 * are the points.
	 * 
	 * @return {@link Map} of violation points.
	 */
	Map<String, Integer> getViolationPoints();

}
