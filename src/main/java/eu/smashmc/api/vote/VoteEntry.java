package eu.smashmc.api.vote;

import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntry {

	/**
	 * Retrieve the player name, who voted
	 *
	 */
	private String playerName;

	/**
	 * Get timestamp when the vote has been sent.
	 *
	 */
	private long timestamp;

	/**
	 * Get the name on the site, where the player has been vote.
	 *
	 */
	private String serviceName;

	/**
	 * Get the address of the voter NOTE: Some vote sites do not send the complete
	 * address, they send for example x.x.x.0
	 *
	 */
	private InetAddress address;

}
