package eu.smashmc.api.maps.game.voting;

import eu.smashmc.api.maps.map.MinecraftMapOverview;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public interface MapVotingIntegration {
	CompletableFuture<List<MinecraftMapOverview>> startVoting();

	MinecraftMapOverview endVoting();

	void cancelVoting();

	void openVotingInventory(Player player);

	ItemStack getVotingItem();

	List<MinecraftMapOverview> getCurrentMapPool();

	Map<MinecraftMapOverview, Integer> getVotes();

	void vote(Player player, MinecraftMapOverview map);

	boolean isVotingActive();

	void removePlayer(Player player);

	void addVotedListener(BiConsumer<Player, MinecraftMapOverview> votedConsumer);
}
