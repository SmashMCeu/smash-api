package eu.smashmc.api.maps.map.position;

import org.bukkit.block.Block;

import java.util.Optional;

public interface MapBlockPosition {

	int getX();

	int getY();

	int getZ();

	/**
	 * Empty if map not loaded
	 */
	Optional<Block> toBlock();

}
