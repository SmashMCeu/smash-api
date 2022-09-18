package eu.smashmc.api.groupsync;

import java.util.UUID;

/**
 * A {@link PlatformUser} is representing an account on some platform, e.g.
 * Discord or Teamspeak that is linked with a specific Minecraft account.
 * 
 * @author Packsolite
 *
 * @param <ID> the type of the platforms unique user identifier
 */
public interface PlatformUser<ID> {

	/**
	 * The platform type of this user.
	 * 
	 * @return platform type
	 */
	Platform getPlatform();

	/**
	 * The id the user is linked to on the platform.
	 * 
	 * @return platform user id
	 */
	ID getIdOnPlatform();

	/**
	 * The name of the user on the platform.
	 * 
	 * @return platform user name
	 */
	String getNameOnPlatform();

	/**
	 * The minecraft {@link UUID} this user belongs to.
	 * 
	 * @return minecraft {@link UUID}
	 */
	UUID getIdInMinecraft();
}
