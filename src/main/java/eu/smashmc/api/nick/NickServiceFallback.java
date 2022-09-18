package eu.smashmc.api.nick;

import org.bukkit.entity.Player;

class NickServiceFallback implements NickService<Player> {

	@Override
	public boolean isNicked(Player player) {
		return false;
	}

	@Override
	public NickInfo getNickInfo(Player player) {
		return null;
	}
}
