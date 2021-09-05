package eu.smashmc.api.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntry {

    /**
     * Retrieve the player name, who voted
     *
     * @return player's name
     */
    private String playerName;

    /**
     * Get timestamp when the vote has been sent.
     *
     * @return timestamp
     */
    private long timestamp;

    /**
     * Get the name on the site, where the player has been vote.
     *
     * @return the site name
     */
    private String serviceName;

    /**
     * Get the address of the voter
     * NOTE: Some vote sites do not send the complete address, they send for example x.x.x.0
     *
     * @return the address as {@link InetAddress}}
     */
    private InetAddress address;

}
