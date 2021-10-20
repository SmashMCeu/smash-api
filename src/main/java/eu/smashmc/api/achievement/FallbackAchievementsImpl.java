package eu.smashmc.api.achievement;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class FallbackAchievementsImpl implements Achievements {

	@Override
	public List<AchievementEntity> listAchievements() {
		return Collections.emptyList();
	}

	@Override
	public List<AchievementEntity> getAchievements(UUID uuid) {
		return Collections.emptyList();
	}

	@Override
	public void claimAchievement(UUID uuid, String achievementKey) throws IllegalArgumentException {
	}

	@Override
	public boolean hasAchievement(UUID uuid, String achievementKey) {
		return true;
	}

	@Override
	public void registerAchievement(AchievementEntity achievementEntity) {
	}

	@Override
	public boolean hasAllAchievements(UUID uuid) {
		return false;
	}
}
