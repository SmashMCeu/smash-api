package eu.smashmc.api.economy;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.Nullable;

import lombok.Data;
import lombok.NonNull;

/**
 * Representing a single operation for a specific account and currency.
 * 
 * @author LiquidDev
 *
 */
@Data
public class Trade {

	@NonNull
	private final UUID account;
	@NonNull
	private final Currency currency;
	private final double amount;
	private final String reason;
	private final String description;

	@Nullable
	private final Long id;
	@Nullable
	private final LocalDateTime date;

	public Trade(UUID account, Currency currency, double amount, String reason, String description) {
		this.account = account;
		this.currency = currency;
		this.amount = amount;
		this.reason = reason;
		this.description = description;

		this.id = null;
		this.date = null;
	}
}
