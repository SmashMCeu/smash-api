package eu.smashmc.api.maps.map.position;

import org.bukkit.Location;

import java.util.Optional;

public interface MapPosition {

	String getTypeId();

	double getX();

	double getY();

	double getZ();

	float getYaw();

	float getPitch();

	/**
	 * Empty if map not loaded
	 */
	Optional<Location> toLocation();
}
