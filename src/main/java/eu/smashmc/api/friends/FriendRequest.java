package eu.smashmc.api.friends;

import java.time.LocalDateTime;
import java.util.UUID;

public interface FriendRequest {

    public UUID getRequester();

    public LocalDateTime getWhenCreated();

}
