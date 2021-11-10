package eu.smashmc.api.friends;

public interface FriendSettings {

    public boolean isAllowingJump();

    public FriendAllowType getPrivateMessage();

    public boolean isAllowingFriendRequests();

    public FriendAllowType getPartyRequest();

}
