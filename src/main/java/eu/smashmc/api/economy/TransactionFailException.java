package eu.smashmc.api.economy;

import lombok.Getter;

/**
 * Exception thrown if a transaction fails.
 * 
 * @author LiquidDev
 *
 */
@Getter
public class TransactionFailException extends Exception {

	private static final long serialVersionUID = 1L;

	private Transaction transaction;
	private Trade[] failedTrades;

	public TransactionFailException(Transaction transaction, Trade[] failed, String reason) {
		super(reason);
		this.transaction = transaction;
		this.failedTrades = failed;
	}
}
