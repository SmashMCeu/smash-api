package eu.smashmc.api.friends;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FriendAllowType {
    ALL(1), ONLY_FRIENDS(2), NOBODY(3);

    private final int id;

    public static FriendAllowType fromId(int id) {
        for(FriendAllowType friendAllowType : values()) {
            if(friendAllowType.getId() == id) {
                return friendAllowType;
            }
        }
        return FriendAllowType.ALL;
    }

}
