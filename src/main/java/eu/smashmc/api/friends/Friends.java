package eu.smashmc.api.friends;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = { Environment.BUKKIT }, fallbackImpl = FallbackFriendsImpl.class)
public interface Friends {

    /**
     * Retrieve a players friend user information from their {@link UUID}.
     *
     * @param uuid the players {@link UUID}
     * @return the {@link FriendUser}
     */
    public CompletableFuture<? extends FriendUser> getFriendUser(UUID uuid);

}
