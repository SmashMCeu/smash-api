package eu.smashmc.api.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPoolDispatcher implements AsyncDispatcher {
	private static ExecutorService executor;

	public ThreadPoolDispatcher() {
		executor = Executors.newCachedThreadPool((new ThreadFactoryBuilder()).setNameFormat("Smash Plugin Thread - %1$d")
				.build());
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