package eu.smashmc.api.friends;

import java.time.LocalDateTime;
import java.util.UUID;

public interface FriendRequest {

    public UUID getRequester();

    public String getName();

    public LocalDateTime getWhenCreated();

    public boolean isOnline();

}
