package eu.smashmc.api.core.packet;

import java.util.function.Consumer;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(Environment.BUKKIT)
public interface PacketEventService {

	<T> void registerListener(Class<T> packetType, Consumer<PacketEvent<T>> listener);

}
