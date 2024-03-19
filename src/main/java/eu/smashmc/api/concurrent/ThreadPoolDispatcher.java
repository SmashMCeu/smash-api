package eu.smashmc.api.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadPoolDispatcher implements AsyncDispatcher {
	private static ExecutorService executor;

	public ThreadPoolDispatcher() {
		executor = Executors.newCachedThreadPool((new ThreadFactoryBuilder()).setNameFormat("smash-api-thread-%d")
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
	public Executor getExecutor() {
		return executor;
	}

	@Override
	public <T> CompletableFuture<T> supply(Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, executor);
	}
}