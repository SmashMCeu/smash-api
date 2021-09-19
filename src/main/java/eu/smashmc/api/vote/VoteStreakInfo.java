package eu.smashmc.api.vote;

import java.util.UUID;

import lombok.Data;

@Data
public class VoteStreakInfo {

	private final UUID uuid;
	private int voteCounter;
	private long lastVote;
}
