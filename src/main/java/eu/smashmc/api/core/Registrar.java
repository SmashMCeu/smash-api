package eu.smashmc.api.core;

@FunctionalInterface
public interface Registrar<T> {

	public abstract void register(T instance);

}
