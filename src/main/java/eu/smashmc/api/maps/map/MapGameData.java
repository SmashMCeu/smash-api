package eu.smashmc.api.maps.map;

import eu.smashmc.api.maps.map.position.MapBlockPosition;
import eu.smashmc.api.maps.map.position.MapPosition;
import org.bukkit.Location;

import java.util.List;
import java.util.Optional;

public interface MapGameData {

	List<MapPosition> getPositions();

	Optional<MapBlockPosition> getHighestBlock();

	Optional<MapBlockPosition> getLowestBlock();

	List<MapPosition> getPositionsOfType(String typeId);

	List<Location> getPositionsAsLocationsOfType(String typeId);

}
