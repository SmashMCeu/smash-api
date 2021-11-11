package eu.smashmc.api.friends;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Friendship {

    public UUID getUuid();

    public String getName();

    public LocalDateTime getWhenCreated();

    public boolean isOnline();

}
