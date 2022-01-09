package eu.smashmc.api.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import javax.annotation.Nullable;

public class Register {

	private static Map<Class<?>, Object> REGISTERED_INSTANCES = new HashMap<>();
	private static Collection<Object> INJECTIONS = new ArrayList<>();
	private static Map<Class<?>, List<Registrar<?>>> REGISTRARS = new HashMap<>();

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

	public static <T> void addRegistrar(Class<T> superType, Registrar<? extends T> registrar) {
		List<Registrar<?>> registrars = REGISTRARS.get(superType);
		if (registrars == null) {
			registrars = new ArrayList<>(1);
			REGISTRARS.put(superType, registrars);
		}
		registrars.add(registrar);
	}

	public static <T> List<Registrar<T>> getApplicableRegistrars(Class<T> type) {
		List<Registrar<T>> applicable = new ArrayList<>();
		for (Entry<Class<?>, List<Registrar<?>>> entry : REGISTRARS.entrySet()) {
			Class<?> superType = entry.getKey();
			List<Registrar<?>> registrars = entry.getValue();
			if (superType.isAssignableFrom(type)) {
				registrars.stream()
						.map(r -> (Registrar<T>) r)
						.forEach(applicable::add);
			}
		}
		return applicable;
	}

	public static void bind(Object object) {
		INJECTIONS.add(object);
	}

	@Nullable
	public static <T> T resolveBind(Class<? super T> type) {
		for (Object obj : INJECTIONS) {
			if (type.isInstance(obj)) {
				return (T) obj;
			}
		}
		return null;
	}
}
