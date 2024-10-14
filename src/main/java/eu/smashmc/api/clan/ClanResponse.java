package eu.smashmc.api.clan;

public interface ClanResponse<T extends Clan> {

    public static enum Result {
        SUCCESS, // The action was successful
        FAIL_ACTION_NOT_RESOLVABLE,
        FAIL_CLAN_NOT_FOUND,
        FAIL_MEMBER_NOT_IN_CLAN,
        FAIL_MEMBER_ALREADY_IN_CLAN,
        FAIL_CLAN_DUPLICATE_NAME,
        FAIL_CLAN_DUPLICATE_TAG,
		FAIL_CLAN_LEAVE_NOT_POSSIBLE,
		FAIL_INVITE_ALREADY_EXISTS,
		FAIL_INVITE_NOT_FOUND,
		FAIL_NOT_ENOUGH_PERMISSIONS,
        FAIL_UNKNOWN_USER,
        FAIL_OTHER; // Other

    }

    Result getResult();
    String getMessage();
	T getClan(); // null if failed

}
