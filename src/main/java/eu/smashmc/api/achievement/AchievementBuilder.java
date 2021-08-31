package eu.smashmc.api.achievement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Builder to create a basic {@link AchievementEntity} for convenience.
 * 
 * @author LiquidDev
 *
 */
public class AchievementBuilder {

	/**
	 * Create a new {@link AchievementBuilder}. Please be sure to at least call
	 * {@link AchievementBuilder#key(String)} before
	 * {@link AchievementBuilder#build()}.
	 * 
	 * @return new {@link AchievementBuilder}
	 */
	public static AchievementBuilder newBuilder() {
		return new AchievementBuilder();
	}

	private AchievementBuilder() {
	}

	private String key;
	private String type = "global";
	private AchievementDifficulty difficulty = AchievementDifficulty.EASY;
	private String description = "";

	/**
	 * Set the achievements key. This is required before calling
	 * {@link AchievementBuilder#build()}.
	 * 
	 * @param key the achievement key
	 * @return this
	 */
	public AchievementBuilder key(String key) {
		this.key = key;
		return this;
	}

	/**
	 * Set the achievement type. This should be the lower case name of the game
	 * mode, e.g. "smash".
	 * 
	 * @param type the achievement type
	 * @return this
	 */
	public AchievementBuilder type(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Set the achievements difficulty. Defaults to
	 * {@link AchievementDifficulty#EASY}.
	 * 
	 * @param difficulty the achievements difficulty
	 * @return this
	 */
	public AchievementBuilder difficulty(AchievementDifficulty difficulty) {
		this.difficulty = difficulty;
		return this;
	}

	/**
	 * Set the achievements description. Language keys are supported. Empty by
	 * default.
	 * 
	 * @param description the achievements description
	 * @return this
	 */
	public AchievementBuilder description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Build the achievement with the assigned values. Requires
	 * {@link AchievementBuilder#key(String)} to be used before.
	 * 
	 * @return this
	 * @throws IllegalArgumentException if key is not set.
	 */
	public AchievementEntity build() throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("key not set");
		}
		return new Achievement(key, type, difficulty, description);
	}

	/**
	 * Very basic implementation of an {@link AchievementEntity} used for
	 * convenience.
	 * 
	 */
	@Getter
	@EqualsAndHashCode
	@AllArgsConstructor
	static class Achievement implements AchievementEntity {
		String key;
		String type;
		AchievementDifficulty difficulty;
		String description;
	}
}
