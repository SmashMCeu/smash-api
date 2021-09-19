package eu.smashmc.api.vote;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@SmashComponent(Environment.BUKKIT)
public interface VoteStreakProvider {
    /**
     * Get player's vote streak
     * NOTE: don't call from bukkit main thread, database-activity
     *
     * @param uuid player's uuid
     * @return the vote streak
     * @throws IllegalStateException if called from main thread
     */
    int getVoteStreak(UUID uuid);
}
