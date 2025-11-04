package eu.smashmc.api.maps;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;
import eu.smashmc.api.maps.game.MapsGameIntegrations;
import eu.smashmc.api.maps.map.MinecraftMap;
import eu.smashmc.api.maps.map.MinecraftMapOverview;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SmashComponent(Environment.BUKKIT)
public interface MapsComponent {

	MapsGameIntegrations setupIntegrations(MapsGameContext gameContext);

	MapsGameIntegrations getIntegrations() throws IllegalStateException;

	void openMapBrowser(Player player, Consumer<MinecraftMapOverview> mapClickedConsumer, String clickAction);

	List<MinecraftMap> getLoadedMaps();

	Optional<MinecraftMap> getMapByWorld(World world);

	/**
	 * If the default world was loaded using sekai, this returns the instance of the map.
	 * To use this feature, set 'load-default-world' to true in sekai-core config.
	 *
	 * @return The default world
	 */
	Optional<MinecraftMap> getDefaultWorldAsMap();

}
