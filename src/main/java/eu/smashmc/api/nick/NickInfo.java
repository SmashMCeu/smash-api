package eu.smashmc.api.nick;

import eu.smashmc.api.identity.minecraft.MinecraftIdentity;

public interface NickInfo {

	MinecraftIdentity getRealIdentity();

	MinecraftIdentity getNickIdentity();

	String getFakeGroup();

}
