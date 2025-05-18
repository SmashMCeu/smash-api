package eu.smashmc.api.maps.map;

import eu.smashmc.api.identity.minecraft.MinecraftIdentity;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public interface MinecraftMapOverview {

	String getDatabaseId();

	String getName();

	String getTypeId();

	MinecraftIdentity getOwner();

	ItemStack getItemStack();

	Instant getDateCreated();

	Instant getDateUpdated();

	MapStatus getStatus();

	MapVisibility getVisibility();

	int getTimesPlayed();

	boolean isApproved();

	String getHumanId();

	String getQuotedHumanId();

	String getDisplayName();

	CompletableFuture<MinecraftMap> fetch();

	int getContributorCount();

}
