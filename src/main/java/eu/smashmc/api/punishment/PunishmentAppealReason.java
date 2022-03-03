package eu.smashmc.api.punishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PunishmentAppealReason {

	FALSE_BAN(false), REDUCTION(false), OTHER(true);

	private final boolean requireComment;
}