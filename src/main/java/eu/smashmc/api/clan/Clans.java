package eu.smashmc.api.clan;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = { Environment.BUKKIT }, fallbackImpl = FallbackClansImpl.class)
public interface Clans {

    CompletableFuture<? extends ClanResponse> findClanByMember(UUID uuid);

}
