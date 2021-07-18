package eu.smashmc.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A global (static use only) API registry for {@link SmashApi}.
 * 
 * @author LiquidDev
 */
public final class SmashMc {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private SmashMc() {
	}

	/**
	 * The actual API registry map with the API type as the key and the
	 * implementation as the value.
	 */
	private static final Map<Class<?>, Object> REGISTERED_APIS = new HashMap<>();

	/**
	 * Registers a new API with it's implementation. An API must have the
	 * {@link SmashApi} annotation.
	 * 
	 * @param <T>            generic type of the APIs interface
	 * @param type           class type of the APIs interface
	 * @param implementation the implementing instance
	 * @throws IllegalArgumentException if the class is not a {@link SmashApi}
	 */
	public static <T> void registerApi(Class<T> type, T implementation) throws IllegalArgumentException {
		if (!type.isAnnotationPresent(SmashApi.class)) {
			throw new IllegalArgumentException("Class is missing the SmashApi annotation: " + type.getName());
		}
		if (implementation == null) {
			throw new IllegalArgumentException("implementation must not be null");
		}
		REGISTERED_APIS.put(type, implementation);
	}

	/**
	 * Retrieve an API instance by their interface class. Note that there is no
	 * guarantee that the requested API is implemented or even present at runtime.
	 * 
	 * @param <T> generic type of the APIs interface
	 * @param api class type of the APIs interface used to lookup
	 * @return an instance of the API
	 * @throws UnsupportedOperationException if the API is not present or not yet
	 *                                       registered
	 * @throws IllegalArgumentException      if the requested type is not a
	 *                                       {@link SmashApi}
	 */
	public static <T> T getApi(Class<T> api) throws UnsupportedOperationException, IllegalArgumentException {
		if (REGISTERED_APIS.containsKey(api)) {
			verifyCompatibility(api);
			Object impl = REGISTERED_APIS.get(api);
			return (T) impl;
		} else {
			if (api.isAnnotationPresent(SmashApi.class)) {
				throw new UnsupportedOperationException(api.getName() + " is not implemented");
			} else {
				throw new IllegalArgumentException(api.getName() + " is not a SmashApi");
			}
		}
	}

	static void verifyCompatibility(Class<?> api) {
		SmashApi annotation = api.getAnnotation(SmashApi.class);
		Environment[] supported = annotation.value();
		for (Environment env : supported) {
			if (Environment.isSupported(env)) {
				return;
			}
		}
		String supportedNames = Stream.of(supported).map(e -> e.name()).collect(Collectors.joining(" / "));
		throw new UnsupportedOperationException(api.getSimpleName() + " requires " + supportedNames);
	}

	static void clearApis() {
		REGISTERED_APIS.clear();
	}
}
