package eu.smashmc.api.vanish;

/**
 * Vanish listener for vanish events.
 * 
 * @param <T> type of Player
 */
public interface VanishListener<T> {
	public void onVanishEvent(VanishEvent<T> event);
}
