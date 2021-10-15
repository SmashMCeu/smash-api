package eu.smashmc.api.vote;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class VoteContainer {

    private final List<VoteEntry> voteEntries = new ArrayList<>();
    /**
     * Get the uuid of the player who sends all votes
     *
     * @return players' uuid
     */
    @Getter private final UUID uuid;

    /**
     * Clear all contained votes
     */
    public void clear() {
        this.voteEntries.clear();
    }

    /**
     * Add a vote to the container
     */
    public void addVote(@NonNull VoteEntry vote) {
        this.voteEntries.add(vote);
    }

    /**
     * Get all votes
     *
     * @return a immutable list of all votes
     */
    public List<VoteEntry> getVotes() {
        return Collections.unmodifiableList(this.voteEntries);
    }


}
