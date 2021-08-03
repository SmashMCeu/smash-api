package eu.smashmc.api.identity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import eu.smashmc.api.concurrent.AsyncExecutor;

/**
 * A id to name, name to id and UUID provider for users.
 * 
 * The values may be cached locally or remote depending on the implementation.
 * 
 * @author LiquidDev
 * @param <ID> type of the user identifier
 * @param <I>  type of the identity
 *
 */
public interface IdentityProvider<ID, I extends Identity<ID>> {

	/**
	 * Retrieves the whole Identity of a user by their id. Returns <code>null</code>
	 * if the user was not found. This is a blocking operation that may perform
	 * networking and database IO on the calling thread.
	 * 
	 * @param uuid the identifier of the user
	 * @return the users identity or <code>null</code>
	 */
	@Nullable
	I getIdentity(ID uuid);

	/**
	 * Retrieves the whole Identities of all users with the given name. Returns an
	 * empty list if no user was found. This is a blocking operation that may
	 * perform networking and database IO on the calling thread.
	 * 
	 * @param name the name of the user
	 * @return a users identity or <code>null</code>
	 */
	List<I> getIdentities(String name);

	/**
	 * Retrieves the whole Identity of a user by their id as a non-blocking
	 * {@link CompletableFuture}. The result of the {@link CompletableFuture} will
	 * be <code>null</code> if the user was not found.
	 * 
	 * @param uuid the identifier of the user
	 * @return the users identity or <code>null</code>
	 */
	default CompletableFuture<I> getIdentityAsync(ID uuid) {
		return AsyncExecutor.supply(() -> this.getIdentity(uuid));
	}

	/**
	 * Retrieves the whole Identity of a user by their name as a non-blocking
	 * {@link CompletableFuture}. The result of the {@link CompletableFuture} will
	 * bean empty list if the user was not found.
	 * 
	 * @param name the name of the user
	 * @return a users identity or <code>null</code>
	 */
	default CompletableFuture<List<I>> getIdentityAsync(String name) {
		return AsyncExecutor.supply(() -> this.getIdentities(name));
	}
}
