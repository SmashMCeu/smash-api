package eu.smashmc.api.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class VoteEntry {

    /**
     * Retrieve the player uuid, who voted
     *
     * @return player's uuid
     */
    private UUID uuid;

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

}
