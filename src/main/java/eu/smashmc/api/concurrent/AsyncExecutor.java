package eu.smashmc.api.concurrent;

import eu.smashmc.api.SmashMc;
import lombok.Getter;
import lombok.Synchronized;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Executors that do NOT use the CommonThreadPool. They can be used for stuff
 * like blocking database operations.
 *
 * @author LiquidDev
 */
public class AsyncExecutor {

	@Getter
	private static AsyncDispatcher dispatcher;

	@Synchronized
	public static void setDispatcher(AsyncDispatcher newDispatcher) {
		/* Shut down existing dispatcher */
		if (dispatcher != null) {
			dispatcher.shutdown();
		}
		SmashMc.registerComponent(AsyncDispatcher.class, newDispatcher);
		dispatcher = newDispatcher;
	}

	public static void execute(Runnable runnable) {
		verifyDispatcher();
		dispatcher.execute(runnable);
	}

	public static <T> Future<T> submit(Callable<T> task) {
		verifyDispatcher();
		return dispatcher.submit(task);
	}

	public static <T> CompletableFuture<T> supply(Supplier<T> supplier) {
		verifyDispatcher();
		return dispatcher.supply(supplier)
				.exceptionally(ex -> {
					ex.printStackTrace();
					throw new RuntimeException(ex);
				});
	}

	@Synchronized
	public static void shutdown() {
		dispatcher.shutdown();
	}

	@Synchronized
	private static void verifyDispatcher() {
		if (dispatcher == null) {
			dispatcher = new ThreadPoolDispatcher();
		}
	}
}
