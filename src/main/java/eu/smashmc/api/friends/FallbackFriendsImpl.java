package eu.smashmc.api.friends;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class FallbackFriendsImpl implements Friends {

    @Override
    public CompletableFuture<FriendUser> getFriendUser(UUID uuid) {
        return CompletableFuture.completedFuture(new FriendUser() {
            @Override
            public UUID getUuid() {
                return uuid;
            }

            @Override
            public List<FriendRequest> getRequests() {
                return Collections.emptyList();
            }

            @Override
            public List<Friendship> getFriendships() {
                return Collections.emptyList();
            }

            @Override
            public FriendSettings getFriendSettings() {
                return new FriendSettings() {
                    @Override
                    public boolean isAllowingJump() {
                        return true;
                    }

                    @Override
                    public FriendAllowType getPrivateMessage() {
                        return FriendAllowType.ONLY_FRIENDS;
                    }

                    @Override
                    public boolean isAllowingFriendRequests() {
                        return true;
                    }

                    @Override
                    public FriendAllowType getPartyRequest() {
                        return FriendAllowType.ALL;
                    }
                };
            }
        });
    }

}
