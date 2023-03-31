package eu.smashmc.api.core.packet;

import java.util.function.Consumer;

import eu.smashmc.api.SmashMc;

public class PacketUtil {
	public static <T> void listenPacket(Class<T> packetType, Consumer<PacketEvent<T>> listener) {
		SmashMc.getComponent(PacketEventService.class)
				.registerListener(packetType, listener);
	}
}
