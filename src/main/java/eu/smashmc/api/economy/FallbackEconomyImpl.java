package eu.smashmc.api.economy;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class FallbackEconomyImpl implements Economy {

	@Override
	public double getBalance(UUID accountUuid, Currency currency) {
		return 0;
	}

	@Override
	public CompletableFuture<Void> submitTransaction(Transaction transaction) {
		return CompletableFuture.completedFuture(null);
	}

}
