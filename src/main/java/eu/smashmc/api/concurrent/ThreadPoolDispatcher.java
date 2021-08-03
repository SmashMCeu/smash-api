package eu.smashmc.api.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class ThreadPoolDispatcher implements AsyncDispatcher {
	private static ExecutorService executor;

	public ThreadPoolDispatcher() {
		executor = Executors.newCachedThreadPool();
	}

	public void execute(Runnable runnable) {
		executor.execute(runnable);
	}

	public <T> Future<T> submit(Callable<T> runnable) {
		return executor.submit(runnable);
	}

	public void shutdown() {
		executor.shutdown();
	}

	@Override
	public <T> CompletableFuture<T> supply(Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, executor);
	}
}