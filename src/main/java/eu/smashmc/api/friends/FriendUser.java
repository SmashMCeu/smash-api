package eu.smashmc.api.friends;

import java.util.List;
import java.util.UUID;

public interface FriendUser {

    /**
     * Get the {@link UUID} of the player the friend user informations belongs to.
     *
     * @return {@link UUID} of the owning player
     */
    public UUID getUuid();

    /**
     * Get the name of the player the friend user informations belongs to {@link String}.
     *
     * @return {@link String} name of the owning player
     */
    public String getName();

    /**
     * Get the current online state of the player the friend user informations belongs to {@link Boolean}.
     *
     * @return {@link Boolean} online state of the owning player
     */
    public boolean isOnline();

    /**
     * Get a list of all pending friend requests {@link FriendRequest}.
     *
     * @return {@link List} with {@link FriendRequest} as element
     */
    public List<FriendRequest> getRequests();

    /**
     * Get a list of all current friendship with other players {@link Friendship}.
     *
     * @return {@link List} with {@link Friendship} as element
     */
    public List<Friendship> getFriendships();

    /**
     * Get the {@link FriendSettings} of the friend user.
     *
     * @return {@link FriendSettings} of the owning player
     */
    public FriendSettings getFriendSettings();

}
