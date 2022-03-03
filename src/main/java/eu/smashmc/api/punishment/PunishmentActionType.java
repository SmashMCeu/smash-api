package eu.smashmc.api.punishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
public enum PunishmentActionType {

	BAN(true), MUTE(true), KICK(false);
	/* SKIN_BAN(true), SHADOW_BAN(true) */;

	@Accessors(fluent = true)
	private final boolean hasDuration;
}
