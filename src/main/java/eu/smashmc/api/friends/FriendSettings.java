package eu.smashmc.api.friends;

public interface FriendSettings {

    public boolean isAllowingJump();

    public FriendAllowType getPrivateMessageType();

    public boolean isAllowingFriendRequests();

    public FriendAllowType getPartyRequestType();

}
