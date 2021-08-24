package eu.smashmc.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lombok.NonNull;

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
	 * Map holding components that are not yet initialized.
	 */
	private static final Map<Class<?>, Supplier<?>> LAZY_COMPONENTS = new HashMap<>();

	/**
	 * The actual component registry map with the component type as the key and the
	 * implementation as the value.
	 */
	private static final Map<Class<?>, Object> INITIALIZED_COMPONENTS = new HashMap<>();

	/**
	 * Registers a new component with it's implementation. A component must have the
	 * {@link SmashComponent} annotation.
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
	public static <T> void registerComponent(Class<T> type, @NonNull T implementation) throws IllegalArgumentException, UnsupportedOperationException {
		if (!type.isAnnotationPresent(SmashComponent.class)) {
			throw new IllegalArgumentException("Class is missing the SmashComponent annotation: " + type.getName());
		}
		verifyCompatibility(type);
		INITIALIZED_COMPONENTS.put(type, implementation);
		Logger logger = Logger.getLogger(SmashMc.class.getName());
		logger.info("Registered component " + type.getSimpleName());
	}

	/**
	 * Registers a component that is later created when needed using the given
	 * {@link Supplier}. The created component instance will then be cached and
	 * reused. A component must have the {@link SmashComponent} annotation.
	 * 
	 * @param <T>                    generic type of the Components interface
	 * @param type                   class type of the Components interface
	 * @param implementationSupplier {@link Supplier} suppling the components
	 *                               implementation
	 * @throws IllegalArgumentException      if the class type is not a
	 *                                       {@link SmashComponent} or the
	 * @throws UnsupportedOperationException if the current environment is not
	 *                                       supported by Component
	 */
	public static <T> void registerLazyComponent(Class<T> type, @NonNull Supplier<T> implementationSupplier) throws IllegalArgumentException, UnsupportedOperationException {
		if (!type.isAnnotationPresent(SmashComponent.class)) {
			throw new IllegalArgumentException("Class is missing the SmashComponent annotation: " + type.getName());
		}
		verifyCompatibility(type);
		LAZY_COMPONENTS.put(type, implementationSupplier);
		Logger logger = Logger.getLogger(SmashMc.class.getName());
		logger.info("Registered lazy component " + type.getSimpleName());
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
		Object impl = INITIALIZED_COMPONENTS.get(component);

		/* Implementation might be lazy loadable */
		if (impl == null) {
			impl = loadComponent(component);
		}

		if (impl != null) {
			return (T) impl;
		} else {
			if (component.isAnnotationPresent(SmashComponent.class)) {
				verifyCompatibility(component);
				throw new IllegalStateException(component.getName() + " is not (yet) registered, missing dependency?");
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
	public static boolean isPresent(Class<?> component) {
		return INITIALIZED_COMPONENTS.containsKey(component) || LAZY_COMPONENTS.containsKey(component);
	}

	@Nullable
	protected static <T> T loadComponent(Class<T> component) {
		Supplier<?> supplier = LAZY_COMPONENTS.get(component);
		if (supplier == null) {
			return null;
		}

		Object obj;
		try {
			obj = supplier.get();
		} catch (Throwable t) {
			throw new RuntimeException("Could not lazy load component " + component.getName(), t);
		}

		T impl = (T) obj;
		LAZY_COMPONENTS.remove(component);
		INITIALIZED_COMPONENTS.put(component, impl);
		Logger logger = Logger.getLogger(SmashMc.class.getName());
		logger.info("Lazy loaded component " + component.getSimpleName());
		return impl;
	}

	protected static void verifyCompatibility(Class<?> component) throws UnsupportedOperationException {
		SmashComponent annotation = component.getAnnotation(SmashComponent.class);
		Environment[] supported = annotation.value();
		for (Environment env : supported) {
			if (Environment.isSupported(env)) {
				return;
			}
		}
		String supportedNames = Stream.of(supported)
				.map(e -> e.name())
				.collect(Collectors.joining(" / "));
		throw new UnsupportedOperationException(component.getSimpleName() + " requires " + supportedNames);
	}

	protected static void clearComponents() {
		INITIALIZED_COMPONENTS.clear();
		LAZY_COMPONENTS.clear();
	}

	@Deprecated
	public static <T> T getApi(Class<T> api) throws UnsupportedOperationException, IllegalArgumentException, IllegalStateException {
		return getComponent(api);
	}
}
