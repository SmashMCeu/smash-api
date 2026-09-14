package eu.smashmc.api.economy;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SmashComponent(value = {Environment.BUKKIT, Environment.BUNGEECORD}, fallbackImpl = FallbackEconomyImpl.class)
public interface Economy {

	/**
	 * Retrieve an accounts balance for the specified currency. This request may
	 * require querying the database.
	 *
	 * @param accountUuid {@link UUID} of the account
	 * @param currency    {@link Currency} to query for
	 * @return the accounts balance for the specified currency
	 */
	double getBalance(UUID accountUuid, Currency currency);

	/**
	 * Submit a transaction to the database. Will complete with a
	 * {@link TransactionFailException} if the transaction failed.
	 * <p>
	 * Be aware that if a single trade fails, the entire transaction is reverted in
	 * the database.
	 *
	 * @param transaction The transaction to be committed
	 * @return CompletableFuture
	 */
	CompletableFuture<Void> submitTransaction(Transaction transaction);

}
