package eu.smashmc.api.stats;

import java.util.List;

import org.bukkit.entity.Player;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(Environment.BUKKIT)
public interface StatsHelper {

	void startGame(String gameType, String mapName, List<Player> participants) throws IllegalStateException;

	void endGame() throws IllegalStateException;

	void addWin(Player player) throws IllegalStateException;

	void addKill(Player player) throws IllegalStateException;

	void addDeath(Player player) throws IllegalStateException;

	void setIntValue(String key, Player player, int value) throws IllegalStateException;

	void setFloatValue(String key, Player player, float value) throws IllegalStateException;

	void setStringValue(String key, Player player, String value) throws IllegalStateException;

	void plusIntValue(String key, Player player, int valueToAdd) throws IllegalStateException;

	void plusFloatValue(String key, Player player, float valueToAdd) throws IllegalStateException;

}
