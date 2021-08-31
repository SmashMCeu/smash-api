package eu.smashmc.api.tos;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.UUID;

public interface AcceptedTos {

	/**
	 * Returns the {@link UUID} of the user that accepted the TOS.
	 * 
	 * @return the users uuid {@link UUID}
	 */
	public UUID getPlayerUuid();

	/**
	 * Returns the {@link InetAddress} that the user had when accepting the TOS.
	 * 
	 * @return the users {@link InetAddress} when accepted
	 */
	public InetAddress getIpWhenAccepted();

	/**
	 * Returns the {@link LocalDateTime} of when the user accepted the TOS.
	 * 
	 * @return time of acceptance
	 */
	public LocalDateTime getDateWhenAccepted();
}
