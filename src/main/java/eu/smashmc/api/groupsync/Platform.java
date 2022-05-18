package eu.smashmc.api.groupsync;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public enum Platform {

	DISCORD("discord", DiscordUser.class), TEAMSPEAK("teamspeak", TeamspeakUser.class);

	public static Platform getByPlatformUserType(Class<? extends PlatformUser<?>> userType) {
		return Stream.of(values())
				.filter(platform -> platform.userType == userType)
				.findAny()
				.get();
	}

	String identifier;

	Class<? extends PlatformUser<?>> userType;
}
