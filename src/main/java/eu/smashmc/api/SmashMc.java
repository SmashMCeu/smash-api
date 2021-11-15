package eu.smashmc.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

	private static final Logger LOGGER = Logger.getLogger(SmashMc.class.getName());

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
			throw new IllegalArgumentException(type.getName() + " is missing the SmashComponent annotation");
		}
		verifyCompatibility(type);
		validateFallbackImplementation(type);
		INITIALIZED_COMPONENTS.put(type, implementation);
		LOGGER.info("Registered component " + type.getSimpleName());
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
			throw new IllegalArgumentException(type.getName() + " is missing the SmashComponent annotation");
		}
		verifyCompatibility(type);
		validateFallbackImplementation(type);
		LAZY_COMPONENTS.put(type, implementationSupplier);
		LOGGER.info("Registered lazy component " + type.getSimpleName());
	}

	/**
	 * Retrieve an component instance by their interface class. Note that there is
	 * no guarantee that the requested component is implemented or even present at
	 * runtime.
	 * 
	 * @param <T>       generic type of the Components interface
	 * @param component class type of the Components interface used to lookup
	 * @return an instance of the component
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

				T fallback = validateFallbackImplementation(component);

				if (fallback != null) {
					LOGGER.warning("Using fallback implementation for " + component.getSimpleName() + ". DO NOT USE IN PRODUCTION!");
					return fallback;
				}
				throw new IllegalStateException(component.getName() + " is not (yet) registered, missing dependency?");
			} else {
				throw new IllegalArgumentException(component.getName() + " is not a SmashComponent");
			}
		}
	}

	/**
	 * Retrieve an component instance by their interface class, or <code>null</code>
	 * in case it is not present. Note that there is no guarantee that the requested
	 * component is implemented or even present at runtime.
	 * 
	 * @param <T>       generic type of the Components interface
	 * @param component class type of the Components interface used to lookup
	 * @return an instance of the component or <code>null</code>
	 * @throws UnsupportedOperationException if the current environment is not
	 *                                       supported by Component
	 */
	@Nullable
	public static <T> T getComponentOrNull(Class<T> component) throws UnsupportedOperationException {
		if (isPresent(component)) {
			return getComponent(component);
		} else {
			return null;
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
		LOGGER.info("Lazy loaded component " + component.getSimpleName());
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
		throw new UnsupportedOperationException(component.getSimpleName() + " requires " + supportedNames + ", current: " + Environment.currentEnvironment());
	}

	protected static <T> T validateFallbackImplementation(Class<T> component) throws IllegalArgumentException, InvalidImplementationException {
		if (!component.isAnnotationPresent(SmashComponent.class)) {
			throw new IllegalArgumentException(component.getName() + " is missing the SmashComponent annotation");
		}
		SmashComponent sc = component.getAnnotation(SmashComponent.class);
		Class<?> fallbackImpl = sc.fallbackImpl();

		/* Object indicates no fallback implementation */
		if (Object.class.equals(fallbackImpl)) {
			return null;
		}

		if (!component.isAssignableFrom(fallbackImpl)) {
			throw new InvalidImplementationException(component.getName() + " is not assignable from " + fallbackImpl.getName());
		}

		Constructor<?> constructor;
		try {
			constructor = fallbackImpl.getDeclaredConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new InvalidImplementationException("Missing or invalid default constructor for fallback implementation " + fallbackImpl.getName(), e);
		}

		Object instance;
		try {
			constructor.setAccessible(true);
			instance = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new InvalidImplementationException("Exception initializing fallback implementation " + fallbackImpl.getName(), e);
		}

		/* This cast should now be safe */
		T obj = (T) instance;
		return obj;
	}

	protected static void clearComponents() {
		INITIALIZED_COMPONENTS.clear();
		LAZY_COMPONENTS.clear();
	}

	@Deprecated(since = "1.2")
	public static <T> T getApi(Class<T> api) throws UnsupportedOperationException, IllegalArgumentException, IllegalStateException {
		return getComponent(api);
	}
}
