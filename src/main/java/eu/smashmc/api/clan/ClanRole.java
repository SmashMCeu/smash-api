package eu.smashmc.api.clan;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum ClanRole {

    LEADER(1, "§4", Set.of(ClanPermission.INVITE, ClanPermission.KICK, ClanPermission.PROMOTE, ClanPermission.DEMOTE, ClanPermission.DELETE)),
    CO_LEADER(2, "§c", Set.of(ClanPermission.INVITE, ClanPermission.KICK, ClanPermission.PROMOTE, ClanPermission.DEMOTE)),
    MODERATOR(3, "§9", Set.of(ClanPermission.INVITE)),
    MEMBER(4, "§e", Set.of());
	
	private final int weight;
	private final String prefix;
	private final Set<ClanPermission> permissions;
	
	public boolean hasPermission(ClanPermission permission) {
		return this.permissions.contains(permission);
	}
	
	public boolean hasPermission(List<ClanPermission> permissions) {
		return this.permissions.containsAll(permissions);
	}
	
	public boolean hasPermission(ClanRole role) {
		return this.weight <= role.weight;
	}
}
