package eu.smashmc.api.clan;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ClanMember {

    UUID getUuid();

    ClanRole getClanRole();

    LocalDateTime getCreationDate();

}