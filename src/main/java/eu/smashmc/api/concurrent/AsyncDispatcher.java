package eu.smashmc.api.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import eu.smashmc.api.SmashComponent;

@SmashComponent
public interface AsyncDispatcher {

	public void execute(Runnable runnable);

	public <T> Future<T> submit(Callable<T> task);

	/**
	 * Runs the given {@link Supplier} on another thread. Note that the
	 * {@link CompletableFuture} returned can complete on the other thread or on the
	 * same, depending on the implementation.
	 * 
	 * @param <T>      type of {@link CompletableFuture}
	 * @param supplier the action to complete on another thread
	 * @return a {@link CompletableFuture} that completes the task
	 */
	public <T> CompletableFuture<T> supply(Supplier<T> supplier);

	public void shutdown();

}