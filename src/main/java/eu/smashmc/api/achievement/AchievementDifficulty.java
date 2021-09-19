package eu.smashmc.api.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AchievementDifficulty {

	EASY(0), MEDIUM(1), HARD(2), IMPOSSIBLE(3);

	private int id;

	public String getLanguageKey() {
		return "difficulty." + name().toLowerCase();
	}
}
