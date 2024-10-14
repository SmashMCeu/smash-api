package eu.smashmc.api.clan;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class FallbackClansImpl implements Clans {
    @Override
    public CompletableFuture<? extends ClanResponse> findClanByMember(UUID uuid) {
        return CompletableFuture.completedFuture(new ClanResponse() {

                    @Override
                    public Result getResult() {
                        return Result.FAIL_MEMBER_NOT_IN_CLAN;
                    }

                    @Override
                    public String getMessage() {
                        return "Member not in clan";
                    }

                    @Override
                    public Clan getClan() {
                        return null;
                    }
                }
        );
    }
}
