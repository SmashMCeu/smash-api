package eu.smashmc.api.core.packet;

import org.bukkit.entity.Player;

import lombok.Data;

@Data
public class PacketEvent {

	private final Player player;
	private Object packet;
	private boolean cancelled;

}
