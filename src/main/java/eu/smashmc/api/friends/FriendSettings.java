package eu.smashmc.api.friends;

public interface FriendSettings {

	boolean isAllowingJump();

	FriendAllowType getPrivateMessageType();

	boolean isAllowingFriendRequests();

	FriendAllowType getPartyRequestType();

	/**
	 * Controls whether the user should see join and quit messages of their friends.
	 *
	 * @return true if player should see join and quit messages of friends.
	 */
	boolean hasJoinQuitMessagesEnabled();
}
