package eu.smashmc.api.core;

public interface Registrar<T> {

	void register(T instance);
	
	void unregister(T instance);

}
