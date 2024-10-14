package eu.smashmc.api.clan;

import java.util.List;
import java.util.UUID;

public interface Clan {

    Long getId();

    String getName();

	String getTag();

	String getTagColor();

	int getMemberLimit();

	List<? extends ClanMember> getMembers();
	
	List<UUID> getInvites();
}