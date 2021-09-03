package eu.smashmc.api.vote;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
public class VoteStreakInfo {

    private final UUID uuid;
    private int voteCounter;
    private long lastVote;
}
