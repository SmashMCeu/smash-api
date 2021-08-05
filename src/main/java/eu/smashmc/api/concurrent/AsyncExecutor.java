package eu.smashmc.api.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import eu.smashmc.api.SmashMc;

/**
 * Executors that do NOT use the CommonThreadPool. They can be used for stuff
 * like blocking database operations.
 * 
 * @author LiquidDev
 */
public class AsyncExecutor {

	private static AsyncDispatcher executor;

	public static void setDispatcher(AsyncDispatcher dispatcher) {
		SmashMc.registerComponent(AsyncDispatcher.class, dispatcher);
		executor = dispatcher;
	}

	public static AsyncDispatcher getDisptacher() {
		return executor;
	}

	public static void execute(Runnable runnable) {
		verfiyDispatcher();
		executor.execute(runnable);
	}

	public static <T> Future<T> submit(Callable<T> task) {
		verfiyDispatcher();
		return executor.submit(task);
	}

	public static <T> CompletableFuture<T> supply(Supplier<T> supplier) {
		verfiyDispatcher();
		return executor.supply(supplier).exceptionally(ex -> {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		});
	}

	public static void shutdown() {
		executor.shutdown();
	}

	private static void verfiyDispatcher() {
		if (executor == null) {
			executor = new ThreadPoolDispatcher();
		}
	}
}
