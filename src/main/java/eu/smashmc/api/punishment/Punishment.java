package eu.smashmc.api.punishment;

import java.util.Optional;
import java.util.UUID;

public interface Punishment {

	/**
	 * The user specific id of the punishment. Multiple punishments can share the
	 * same id across different players. To uniquely identify a punishment you need
	 * the players UUID and as the punishment id.
	 * 
	 * @return user specific punishment id
	 */
	int getId();

	/**
	 * Returns the {@link UUID} of the user the punishment belongs to.
	 * 
	 * @return {@link UUID} of owner
	 */
	UUID getUserUuid();

	/**
	 * Creation time of the punishment in Unix time in milliseconds.
	 * 
	 * @return time stamp of creation
	 */
	long getTimeCreated();

	/**
	 * The reason for the punishment. Can be either a template id or a custom reason
	 * possibly containing spaces or special characters.
	 * 
	 * @return reason of the punishment
	 */
	String getReason();

	/**
	 * Tries to translate the punishment reason or, if it fails, make it human
	 * readable by splitting camel case into multiple words.
	 * 
	 * @param userUuid {@link UUID} of the user to translate for
	 * @return Translated or human readable reason
	 */
	String getTranslatedReason(UUID userUuid);

	/**
	 * The original action of the punishment without any appeals.
	 * 
	 * @return original {@link PunishmentAction}
	 */
	PunishmentAction getOriginalAction();

	/**
	 * The resulting effective punishment action including possible punishment
	 * appeals.
	 * 
	 * @return {@link PunishmentAction} adapted to possible appeals
	 */
	PunishmentAction getEffectiveAction();

	/**
	 * Optional comment further describing the punishment. Moderators are lazy so
	 * expect this to be empty 99% of the time.
	 * 
	 * @return {@link Optional} comment
	 */
	Optional<String> getComment();

	/**
	 * Similar to {@link Punishment#getComment()} this is additional optional
	 * information about the punishments proof. Might be a URL to a ChatLog or
	 * YouTube video.
	 * 
	 * @return {@link Optional} proof for the punishment
	 */
	Optional<String> getProof();

	/**
	 * The users IP address when banned. Might be an IPv4, IPv6 or hostname
	 * depending on the implementation.
	 * 
	 * @return the users IP address when banned
	 */
	String getIp();

	/**
	 * The {@link UUID} of the one who created the punishment. If created by
	 * console, this will return a Null-UUID (all bits = 0).
	 * 
	 * @return {@link UUID} of the creator
	 */
	UUID getPunishedBy();

	/**
	 * {@link Optional} {@link PunishmentAppeal} for the punishment.
	 * 
	 * @return {@link Optional} {@link PunishmentAppeal} for the punishment.
	 */
	Optional<? extends PunishmentAppeal> getAppeal();

	/**
	 * Check if the punishment is still active including appeals.
	 * 
	 * @return <code>true</code> if punishment is still active
	 */
	boolean isActive();

	/**
	 * Returns the effective total duration in milliseconds of the punishment
	 * including appeal and editions. Returns {@link Long#MAX_VALUE} if the
	 * punishment is permanent.
	 * 
	 * Note: creationTime + duration != endTime if an appeal has been created
	 * 
	 * @return effective duration in milliseconds
	 */
	long getDuration();

	/**
	 * Returns the effective time in milliseconds left the punishment will be
	 * active. Might be zero or negative if the punishment has elapsed or is
	 * forgiven.
	 * 
	 * Note: creationTime + duration != endTime if an appeal has been created
	 * 
	 * @return effective time left in milliseconds
	 */
	long getTimeLeft();

	/**
	 * Checks if the punishment was forgiven. A forgiven punishment is inactive
	 * immediately and should not count to a users violation points. The was
	 * previously referred to as "removePoint".
	 * 
	 * @return <code>true</code> if the punishment is forgiven and invalid
	 */
	boolean isForgiven();

	/**
	 * Checks if the punishment is permanent including appeals. A punishment might
	 * still be permanent even if it was appealed and forgiven. Use
	 * {@link Punishment#isActive()} to check if the punishment is still active.
	 * 
	 * @return <code>true</code> if the punishment is permanent
	 */
	boolean isPermanent();
}
