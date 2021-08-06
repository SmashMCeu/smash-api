package eu.smashmc.api.identity;

import java.util.UUID;

/**
 * Identity representing some user with an identifier and a name.
 * 
 * @author LiquidDev
 *
 * @param <ID> generic type of the identifier - a {@link UUID} for example
 */
public interface Identity<ID> {

	/**
	 * The unique identifier of the identity.
	 * 
	 * @return the identities id
	 */
	ID getId();

	/**
	 * The identities name. May or may not be unique to other identities.
	 * 
	 * @return the identities name
	 */
	String getName();
}
