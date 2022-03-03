package eu.smashmc.api.punishment;

public interface PunishmentAction {

	/**
	 * Is the punishment a ban, kick or mute?
	 * 
	 * @return type of punishment
	 */
	PunishmentActionType getType();

	/**
	 * Checks if the punishment action has a duration. E.g. a
	 * {@link PunishmentActionType#KICK} would not have a duration.
	 * 
	 * @return <code>true</code> if action has a duration.
	 */
	boolean hasDuration();

	/**
	 * Duration in milliseconds. Permanent punishments will return
	 * {@link Long#MAX_VALUE}
	 * 
	 * @return Duration of punishment in milliseconds or {@link Long#MAX_VALUE}
	 */
	long getDuration();
}
