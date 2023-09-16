package eu.smashmc.api.nick;

import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

class NickServiceFallback implements NickService<Player> {

	@Override
	public boolean isNicked(Player player) {
		return false;
	}

	@Override
	public Optional<NickInfo> getNick(Player player) {
		return Optional.empty();
	}

	@Override
	public Optional<? extends NickInfo> getInfoAboutNickUuid(UUID nick) {
		return Optional.empty();
	}
}
