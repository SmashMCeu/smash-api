package eu.smashmc.api.economy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import eu.smashmc.api.SmashMc;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Class representing a database operation consisting of multiple
 * {@link Trade}s.
 * 
 * @author LiquidDev
 *
 */
@ToString
public class Transaction {

	@Getter
	private List<Trade> trades;

	public Transaction(@NonNull List<Trade> trades) {
		this.trades = trades;
	}

	public Transaction() {
		this(new ArrayList<>());
	}

	public CompletableFuture<Void> commit() {
		return SmashMc.getComponent(Economy.class)
				.submitTransaction(this);
	}

	public void addBalance(double amount, Currency currency, UUID accountHolder, String reason, String description) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount to deposit must not be negative");
		}
		var trade = new Trade(accountHolder, currency, amount, reason, description);
		this.trades.add(trade);
	}

	public void removeBalance(double amount, Currency currency, UUID accountHolder, String reason, String description) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount to withdraw must not be negative");
		}
		var trade = new Trade(accountHolder, currency, -amount, reason, description);
		this.trades.add(trade);
	}
}
