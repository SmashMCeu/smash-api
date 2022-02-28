package eu.smashmc.api.punishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
public enum PunishmentType {

	KICK(false), MUTE(true), BAN(true);
	/* SKIN_BAN(true), SHADOW_BAN(true) */;

	@Accessors(fluent = true)
	private final boolean hasDuration;
}
