package eu.smashmc.api.achievement;

import org.bukkit.entity.Player;

import eu.smashmc.api.SmashMc;

/**
 * Shortcuts for Spigot plugins to simplify achievements integration.
 * 
 * @author LiquidDev
 *
 */
public class SpigotAchievementsHelper {

	public static void claimAchievement(Player player, String achievement) {
		var achievements = SmashMc.getComponent(Achievements.class);
		achievements.claimAchievement(player.getUniqueId(), achievement);
	}

	public static boolean hasAchievement(Player player, String achievement) {
		var achievements = SmashMc.getComponent(Achievements.class);
		return achievements.hasAchievement(player.getUniqueId(), achievement);
	}

	public static void registerAchievement(AchievementEntity achievement) {
		var achievements = SmashMc.getComponent(Achievements.class);
		achievements.registerAchievement(achievement);
	}

	public static void registerAchievement(String key, String gameType, AchievementDifficulty difficulty, String description) {
		registerAchievement(AchievementBuilder.newBuilder()
				.key(key)
				.type(gameType)
				.difficulty(difficulty)
				.description(description)
				.build());
	}
}
