package eu.smashmc.api.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import javax.annotation.Nullable;

/**
 * Static access to the @AutoInject dependency injection and @AutoRegister
 * registration mechanism.
 * 
 * @author LiquidDev
 */
public class RegistryService {

	private static Map<Class<?>, Object> REGISTERED_INSTANCES = new HashMap<>();
	private static Collection<Object> INJECTIONS = new ArrayList<>();
	private static Map<Class<?>, List<Registrar<?>>> REGISTRARS = new HashMap<>();

	protected static <T> void register(Class<T> clazz, T instance) {
		if (REGISTERED_INSTANCES.containsKey(clazz)) {
			throw new IllegalStateException("An instance of class " + clazz.getName() + " is already registered.");
		}
		REGISTERED_INSTANCES.put(clazz, instance);
	}

	/**
	 * Use this to access an instance of a class instantiated with @AutoRegister.
	 * 
	 * @param <T>   generic class type
	 * @param clazz reference to the class type
	 * @return Instance of the given class found in the registry
	 * @throws NoSuchElementException If no instance was found for the given class
	 *                                type
	 */
	public static <T> T getInstance(Class<T> clazz) throws NoSuchElementException {
		Object object = REGISTERED_INSTANCES.get(clazz);
		if (object == null) {
			throw new NoSuchElementException("No instance of class " + clazz.getName() + " was found. Missing @AutoInject or too early?");
		}
		return (T) object;
	}

	/**
	 * Add a custom registrar used to register classes of a given type on startup.
	 * 
	 * @param <T>       type of class to register
	 * @param superType any supert ype of the classes to be registered.
	 * @param registrar The {@link Registrar} used to register instances
	 */
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

	/**
	 * Add a new dependency injection bind.
	 * 
	 * @param object instance to be injected
	 */
	public static void bind(Object object) {
		INJECTIONS.add(object);
	}

	/**
	 * Find a suitable bind by it's type.
	 * 
	 * @param <T>  type of bind to be resolved
	 * @param type class type of bind to be resolved
	 * @return An applicaple bind or <code>null</code>
	 */
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
