package eu.smashmc.api.maps;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.maps.game.MapsGameIntegrations;
import eu.smashmc.api.maps.map.MinecraftMapOverview;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@SmashComponent(Environment.BUKKIT)
public interface MapsComponent {

	MapsGameIntegrations setupIntegrations(MapsGameContext gameContext);

	MapsGameIntegrations getIntegrations() throws IllegalStateException;

	void openMapBrowser(Player player, Consumer<MinecraftMapOverview> mapClickedConsumer);

}
