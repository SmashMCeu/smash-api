package eu.smashmc.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

/**
 * A global (static use only) component registry for {@link SmashComponent}.
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
	 * The actual component registry map with the component type as the key and the
	 * implementation as the value.
	 */
	private static final Map<Class<?>, Object> REGISTERED_ComponentS = new HashMap<>();

	/**
	 * Registers a new component with it's implementation. An component must have
	 * the {@link SmashComponent} annotation.
	 * 
	 * @param <T>            generic type of the Components interface
	 * @param type           class type of the Components interface
	 * @param implementation the implementing instance
	 * @throws IllegalArgumentException      if the class type is not a
	 *                                       {@link SmashComponent} or the
	 *                                       implementation is <code>null</code>
	 * @throws UnsupportedOperationException if the current environment is not
	 *                                       supported by Component
	 */
	public static <T> void registerComponent(Class<T> type, T implementation) throws IllegalArgumentException, UnsupportedOperationException {
		if (!type.isAnnotationPresent(SmashComponent.class)) {
			throw new IllegalArgumentException("Class is missing the SmashComponent annotation: " + type.getName());
		}
		if (implementation == null) {
			throw new IllegalArgumentException("implementation must not be null");
		}
		verifyCompatibility(type);
		REGISTERED_ComponentS.put(type, implementation);
	}

	/**
	 * Retrieve an component instance by their interface class. Note that there is
	 * no guarantee that the requested component is implemented or even present at
	 * runtime.
	 * 
	 * @param <T>       generic type of the Components interface
	 * @param component class type of the Components interface used to lookup
	 * @return an instance of the component *
	 * @throws IllegalArgumentException      if the requested type is not a
	 *                                       {@link SmashComponent}
	 * @throws UnsupportedOperationException if the current environment is not
	 *                                       supported by Component
	 * @throws IllegalStateException         if the component is not registered
	 *                                       (yet)
	 */
	@Nonnull
	public static <T> T getComponent(Class<T> component) throws UnsupportedOperationException, IllegalArgumentException, IllegalStateException {
		Object impl = REGISTERED_ComponentS.get(component);

		if (impl != null) {
			return (T) impl;
		} else {
			if (component.isAnnotationPresent(SmashComponent.class)) {
				verifyCompatibility(component);
				throw new IllegalStateException(component.getName() + " is not registered");
			} else {
				throw new IllegalArgumentException(component.getName() + " is not a SmashComponent");
			}
		}
	}

	/**
	 * Checks if a given component is registered and ready to use.
	 * 
	 * @param component type of the component to check
	 * @return <code>true</code> if component is registered
	 */
	public boolean isPresent(Class<?> component) {
		return REGISTERED_ComponentS.containsKey(component);
	}

	static void verifyCompatibility(Class<?> component) throws UnsupportedOperationException {
		SmashComponent annotation = component.getAnnotation(SmashComponent.class);
		Environment[] supported = annotation.value();
		for (Environment env : supported) {
			if (Environment.isSupported(env)) {
				return;
			}
		}
		String supportedNames = Stream.of(supported).map(e -> e.name()).collect(Collectors.joining(" / "));
		throw new UnsupportedOperationException(component.getSimpleName() + " requires " + supportedNames);
	}

	static void clearComponents() {
		REGISTERED_ComponentS.clear();
	}

	@Deprecated
	public static <T> T getApi(Class<T> api) throws UnsupportedOperationException, IllegalArgumentException, IllegalStateException {
		return getComponent(api);
	}
}
