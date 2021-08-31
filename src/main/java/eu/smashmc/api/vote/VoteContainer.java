package eu.smashmc.api.vote;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class VoteContainer {

    private final List<VoteEntry> voteEntries = new ArrayList<>();
    /**
     * Get the name of the player who sends all votes
     *
     * @return players' name
     */
    @Getter private final String playerName;

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
