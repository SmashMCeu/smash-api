package eu.smashmc.api.maps.map;

import eu.smashmc.api.identity.minecraft.MinecraftIdentity;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MinecraftMap extends MinecraftMapOverview {

	/**
	 * Checks if the world of this map is loaded.
	 */
	boolean isWorldLoaded();

	/**
	 * If loaded, returns the world of this map.
	 */
	Optional<World> getWorld();

	CompletableFuture<World> loadWorld();

	Set<String> getTags();

	List<MinecraftIdentity> getContributors();

	List<MinecraftIdentity> getMembers();

	Optional<UUID> getVersionId();

	boolean hasViewAccess(Player player);

	boolean hasEditAccess(Player player);

	boolean isMember(Player player);

	boolean isOwner(Player player);

	boolean isContributor(Player player);

	int getMaxBuildHeight();

}
