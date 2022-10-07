package eu.smashmc.api.core.packet;

import org.bukkit.entity.Player;

import lombok.Data;

@Data
public class PacketEvent<T> {

	private final Player player;
	private T packet;
	private boolean cancelled;

}
