package eu.smashmc.api.maps.game;

import de.liquiddev.command.autocomplete.Autocompleter;
import eu.smashmc.api.maps.game.voting.MapVotingIntegration;
import eu.smashmc.api.maps.map.MinecraftMap;
import eu.smashmc.api.maps.map.MinecraftMapOverview;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface MapsGameIntegrations {

	MapVotingIntegration getMapVoting();

	void openPlayableMapSelector(Player player, Consumer<MinecraftMapOverview> mapClickedConsumer);

	Autocompleter<Player> autocompletePlayableMaps();

	/**
	 * Increases the map play count by 1.
	 */
	void playMap(MinecraftMapOverview map);

	/**
	 * Forces a metadata refresh for the given map.
	 */
	CompletableFuture<MinecraftMap> refreshMap(MinecraftMap map);
}
