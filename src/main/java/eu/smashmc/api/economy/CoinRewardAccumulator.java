package eu.smashmc.api.economy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.smashmc.api.lang.CommonTranslations;
import eu.smashmc.api.lang.Lang;
import eu.smashmc.api.lang.LanguageProvider;
import lombok.RequiredArgsConstructor;

/**
 * Helper class to reward players for kills and wins.
 * 
 * @author LiquidDev
 *
 */
@RequiredArgsConstructor
public class CoinRewardAccumulator {

	public static final int KILL_REWARD = 20;
	public static final int GAME_WIN_REWARD = 100;

	/**
	 * Language provider used for chat messages.
	 */
	private final LanguageProvider<CommandSender> language;

	/**
	 * Name of the game mode, e.g. "smash".
	 */
	private final String gameModeName;

	private Map<Player, CoinReward> rewards = new HashMap<>();

	/**
	 * Use other constructor instead.
	 * 
	 * @param gameModeName name of gamemode
	 */
	@Deprecated
	public CoinRewardAccumulator(String gameModeName) {
		this(Lang.findProviderFromCallingClass(), gameModeName);
	}

	/**
	 * Reward a {@link Player} with coins for a won game.
	 * 
	 * @param player {@link Player} to be rewarded
	 * @return the coins that were rewarded.
	 */
	public int rewardWin(Player player) {
		return rewardWin(player, 1);
	}

	/**
	 * Reward a {@link Player} with coins for a kill.
	 * 
	 * @param player {@link Player} to be rewarded
	 * @return the coins that were rewarded.
	 */
	public int rewardKill(Player player) {
		return rewardKill(player, 1);
	}

	/**
	 * Reward a {@link Player} with coins for a won game with a custom multiplier
	 * added as a coin boost.
	 * 
	 * @param player           {@link Player} to be rewarded
	 * @param customMultiplier custom multiplier added as boost
	 * @return the coins that were rewarded.
	 */
	public int rewardWin(Player player, double customMultiplier) {
		int baseCoins = GAME_WIN_REWARD;
		var reward = getReward(player);
		reward.wins++;
		return rewardCoins(player, baseCoins, customMultiplier);
	}

	/**
	 * Reward a {@link Player} with coins for a kill with a custom multiplier added
	 * as a coin boost.
	 * 
	 * @param player           {@link Player} to be rewarded
	 * @param customMultiplier custom multiplier added as boost
	 * @return the coins that were rewarded.
	 */
	public int rewardKill(Player player, double customMultiplier) {
		int baseCoins = KILL_REWARD;
		var reward = getReward(player);
		reward.kills++;
		return rewardCoins(player, baseCoins, customMultiplier);
	}

	/**
	 * Reward a {@link Player} with coins for any reason with a custom multiplier
	 * added as a coin boost.
	 * 
	 * @param player     {@link Player} to be rewarded
	 * @param baseCoins  the coins to be added
	 * @param multiplier the boost multiplier to be applied
	 * @return the coins that were rewarded.
	 */
	public int rewardCoins(Player player, int baseCoins, double multiplier) {
		int boost = 0;

		if (multiplier != 1) {
			boost += baseCoins * (multiplier - 1);
		}

		if (player.hasPermission("smash.pro")) {
			boost += baseCoins / 2;
		}

		if (boost > 0) {
			language.sendMessage(player, CommonTranslations.CHAT_COINS_BOOSTED, baseCoins, boost);
		} else {
			language.sendMessage(player, CommonTranslations.CHAT_COINS, baseCoins);
		}

		var reward = getReward(player);
		reward.coins += baseCoins + boost;
		return baseCoins + boost;
	}

	private CoinReward getReward(Player player) {
		CoinReward reward = rewards.get(player);
		if (reward == null) {
			reward = new CoinReward();
			rewards.put(player, reward);
		}
		return reward;
	}

	/**
	 * Converts all the reward into one economy transaction.
	 * 
	 * @param clear if <code>true</code>, the reward list is cleared
	 * @return the resulting {@link Transaction}
	 */
	public Transaction toTransaction(boolean clear) {
		var transaction = new Transaction();
		for (Map.Entry<Player, CoinReward> entry : rewards.entrySet()) {
			Player player = entry.getKey();
			CoinReward reward = entry.getValue();
			String description = "kills=" + reward.kills + " wins=" + reward.wins;
			transaction.addBalance(reward.coins, Currency.COINS, player.getUniqueId(), gameModeName + " game", description);
		}

		if (clear) {
			rewards.clear();
		}
		return transaction;
	}

	/**
	 * Commits and clears the accumulated coins reward.
	 * 
	 * @return {@link CompletableFuture}
	 */
	public CompletableFuture<Void> commit() {
		return toTransaction(true).commit();
	}

	private static class CoinReward {
		int kills;
		int wins;

		int coins;
	}
}
