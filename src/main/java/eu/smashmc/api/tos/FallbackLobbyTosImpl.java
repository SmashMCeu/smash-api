package eu.smashmc.api.tos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.entity.Player;

class FallbackLobbyTosImpl implements LobbyTos {

	@Override
	public boolean hasAcceptedTos(Player player) {
		return true;
	}

	@Override
	public AcceptedTos getAcceptedTos(Player player) {
		return new AcceptedTos() {

			@Override
			public UUID getPlayerUuid() {
				return player.getUniqueId();
			}

			@Override
			public InetAddress getIpWhenAccepted() {
				try {
					return InetAddress.getByName("0.0.0.0");
				} catch (UnknownHostException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public LocalDateTime getDateWhenAccepted() {
				return LocalDateTime.now();
			}
		};
	}

}
