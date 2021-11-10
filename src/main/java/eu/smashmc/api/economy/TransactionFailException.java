package eu.smashmc.api.economy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception thrown if a transaction fails.
 * 
 * @author LiquidDev
 *
 */
@Getter
@AllArgsConstructor
public class TransactionFailException extends Exception {

	private static final long serialVersionUID = 1L;

	private Transaction transaction;
	private Trade[] failedTrades;
	private String reason;

}
