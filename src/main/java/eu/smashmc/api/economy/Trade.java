package eu.smashmc.api.economy;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Representing a single operation for a specific account and currency.
 * 
 * @author LiquidDev
 *
 */
@Data
@AllArgsConstructor
public class Trade {

	@NonNull
	private final UUID account;
	@NonNull
	private final Currency currency;
	private final double amount;
	private final String reason;
	private final String description;

}
