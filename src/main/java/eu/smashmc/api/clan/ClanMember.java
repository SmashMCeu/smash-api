package eu.smashmc.api.clan;

import java.time.LocalDateTime;

public interface ClanMember {

    String getUuid();

    ClanRole getClanRole();

    LocalDateTime getCreationDate();

}