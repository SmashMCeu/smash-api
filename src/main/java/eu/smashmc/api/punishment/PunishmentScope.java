package eu.smashmc.api.punishment;

public interface PunishmentScope {

	/**
	 * Is the punishment a ban, kick or mute?
	 * 
	 * @return type of punishment
	 */
	PunishmentType getType();

	/**
	 * Checks if the punishment scope has a duration. E.g. a
	 * {@link PunishmentType#KICK} would not have a duration.
	 * 
	 * @return <code>true</code> if scope has a duration.
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
