package eu.smashmc.api;

public enum Environment {
	BUKKIT, BUNGEECORD, NONE;

	private static Environment current;

	public static Environment currentEnvironment() {
		if (current == null) {
			return NONE;
		} else {
			return current;
		}
	}

	public static void setEnvironment(Environment environment) {
		current = environment;
	}

	/**
	 * Check if the given {@link Environment} is supported the current
	 * {@link Environment}. Supported hereby means that the given
	 * {@link Environment} is NONE or the same as the current one.
	 * 
	 * @param environment the required {@link Environment}
	 * @return <code>true</code> if the given environment is supported
	 */
	public static boolean isSupported(Environment environment) {
		// Is it the current environment?
		if (currentEnvironment() == environment) {
			return true;
		}
		// NONE is always supported
		if (NONE == environment) {
			return true;
		}
		return false;
	}
}