package eu.smashmc.api.punishment;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface PunishmentAppeal {

	/**
	 * If forgive is set to <code>true</code> the Punishment is longer active and
	 * does not count as a violation point.
	 * 
	 * @return <code>true</code> if punishment is completly forgiven
	 */
	boolean isForgive();

	/**
	 * The {@link UUID} of who created the appeal. Can be "all 0" {@link UUID} if it
	 * was the console.
	 * 
	 * @return {@link UUID} of creator
	 */
	UUID getBy();

	/**
	 * Creation timestamp of the appeal.
	 * 
	 * @return time stamp of creation
	 */
	Instant getTimeCreated();

	/**
	 * If the appeal overrides the punishment duration, this will contain the new
	 * duration in milliseconds.
	 * 
	 * @return {@link Optional} holding the time in milliseconds or empty
	 */
	Optional<Long> getNewDuration();

	/**
	 * The reason for the appeal or why it was created.
	 * 
	 * @return appeal reason
	 */
	PunishmentAppealReason getReason();

	/**
	 * Can hold some extra information about the appeal.
	 * 
	 * @return extra information or empty
	 */
	Optional<String> getComment();

}
