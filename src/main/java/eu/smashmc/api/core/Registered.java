package eu.smashmc.api.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Registered {

	private static Map<Class<?>, Object> REGISTERED_INSTANCES = new HashMap<>();

	protected static <T> void register(Class<T> clazz, T instance) {
		if (REGISTERED_INSTANCES.containsKey(clazz)) {
			throw new IllegalStateException("Another instance of class " + clazz.getName() + " is already registered.");
		}
		REGISTERED_INSTANCES.put(clazz, instance);
	}

	public static <T> T getInstance(Class<T> clazz) {
		Object object = REGISTERED_INSTANCES.get(clazz);
		if (object == null) {
			throw new NoSuchElementException("No instance of class " + clazz.getName() + " was registered (yet).");
		}
		return (T) object;
	}
}
