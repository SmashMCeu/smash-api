package eu.smashmc.api.maps.map;

import eu.smashmc.api.maps.map.position.MapBlockPosition;
import eu.smashmc.api.maps.map.position.MapPosition;
import org.bukkit.Location;

import java.util.List;

public interface MapGameData {

	List<MapPosition> getPositions();

	MapBlockPosition getHighestBlock();

	MapBlockPosition getLowestBlock();

	List<String> getPositionTypeIds();

	List<MapPosition> getPositionsOfType(String typeId);

	List<Location> getPositionsAsLocationsOfType(String typeId);

}
