package eu.smashmc.api;

import java.util.HashMap;
import java.util.Map;

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
	private static final Map<Class<? extends SmashApi>, SmashApi> REGISTERED_APIS = new HashMap<>();

	/**
	 * Registers a new API with it's implementation.
	 * 
	 * @param <T>            generic type of the APIs interface
	 * @param type           class type of the APIs interface
	 * @param implementation the implementing instance
	 */
	public static <T extends SmashApi> void registerApi(Class<T> type, T implementation) {
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
	 */
	public static <T extends SmashApi> T getApi(Class<T> api) throws UnsupportedOperationException {
		if (REGISTERED_APIS.containsKey(api)) {
			SmashApi impl = REGISTERED_APIS.get(api);
			return (T) impl;
		} else {
			throw new UnsupportedOperationException("Api not implemented: " + api.getName());
		}
	}
}
