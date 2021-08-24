package eu.smashmc.api.profile;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

/**
 * Represents a users profile, stored in a {@link Map} with a {@link String} key
 * and an {@link JsonObject} value.
 * 
 * @author LiquidDev
 */
public interface PlayerProfile {

	/**
	 * Get a map holding all topic names mapped to the topic {@link JsonObject}.
	 * 
	 * @return {@link Map} with {@link String} keys and {@link JsonObject} values
	 */
	Map<String, JsonObject> getTopics();

	/**
	 * Get a topic by it's name. Returns <code>null</code>, if topic does not exist.
	 * 
	 * @param name Name of the topic
	 * @return {@link JsonObject} or <code>null</code>
	 */
	@Nullable
	JsonObject getTopic(String name);

	/**
	 * Checks if a topic with a given name exists. The name is not case sensitive.
	 * 
	 * @param name The name of the topic to check for
	 * @return <code>true</code> if a topic with that name exists.
	 */
	boolean hasTopic(String name);

	/**
	 * Create a new topic, or override an existing one.
	 * 
	 * @param name The name of the new topic.
	 * @return An empty {@link JsonObject} for the topic.
	 */
	JsonObject createTopic(String name);

	/**
	 * Checks if the {@link PlayerProfile} is read-only. In read only mode, it can
	 * not be persisted using PlayerProfileService#updateProfile().
	 * 
	 * @return <code>true</code> if profile is read only
	 */
	boolean isReadonly();

	/**
	 * Gets a topic by it's name, or - in case it does not exist, creates a new one
	 * and returns it.
	 * 
	 * @param name The name of the topic to be retrieved or created
	 * @return {@link JsonObject} topic
	 */
	default JsonObject getOrCreateTopic(String name) {
		return hasTopic(name) ? getTopic(name) : createTopic(name);
	}

}
