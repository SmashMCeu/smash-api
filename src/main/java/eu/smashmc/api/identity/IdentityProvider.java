package eu.smashmc.api.identity;

import java.util.List;
import java.util.Map;
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
	 * @param id the identifier of the user
	 * @return the users identity or <code>null</code>
	 */
	@Nullable
	I getById(ID id);

	/**
	 * Searches all users with the given name. Returns an empty list if no user with
	 * that name was found. This is a blocking operation that may perform networking
	 * and database IO on the calling thread.
	 * 
	 * @param name the name of the user
	 * @return {@link List} of identities with the given name, may be empty
	 */
	List<I> findByName(String name);

	// TODO documentation
	Map<ID, I> getAllById(ID[] ids);

	// TODO documentation
	Map<String, List<I>> findAllByName(String[] names);

	/**
	 * Retrieves the whole Identity of a user by their id as a non-blocking
	 * {@link CompletableFuture}. The result of the {@link CompletableFuture} will
	 * be <code>null</code> if the user was not found.
	 * 
	 * @param id the identifier of the user
	 * @return the users identity or <code>null</code>
	 */
	default CompletableFuture<I> getByIdAsync(ID id) {
		return AsyncExecutor.supply(() -> this.getById(id));
	}

	/**
	 * Searches all users with the given name as a non-blocking
	 * {@link CompletableFuture}. The result of the {@link CompletableFuture} will
	 * be an empty list if no user with that name was found.
	 * 
	 * @param name the name to search for
	 * @return {@link CompletableFuture} holding {@link List} of identities with the
	 *         given name, may be empty
	 */
	default CompletableFuture<List<I>> findByNameAync(String name) {
		return AsyncExecutor.supply(() -> this.findByName(name));
	}

	// TODO documentation
	default CompletableFuture<Map<ID, I>> getAllByIdAsync(ID[] ids) {
		return AsyncExecutor.supply(() -> this.getAllById(ids));
	}

	// TODO documentation
	default CompletableFuture<Map<String, List<I>>> findAllByNameAsync(String[] names) {
		return AsyncExecutor.supply(() -> this.findAllByName(names));
	}
}
