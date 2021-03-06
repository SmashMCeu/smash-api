package eu.smashmc.api.profile;

import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonObject;

/**
 * Represents a users profile, stored in a {@link Map} with a {@link String} key
 * and an {@link JsonObject} value.
 * 
 * @author LiquidDev
 */
public interface PlayerProfile {

	/**
	 * Get the {@link UUID} of the player the profile belongs to.
	 * 
	 * @return {@link UUID} of the owning player
	 */
	UUID getUuid();

	/**
	 * Get a map holding all topic names mapped to the topic {@link JsonObject}.
	 * 
	 * @return {@link Map} with {@link String} keys and {@link JsonObject} values
	 */
	Map<String, JsonObject> getTopics();

	/**
	 * Get a topic by it's name. Creates a new one if the topic does not exist.
	 * 
	 * @param name Name of the topic
	 * @return {@link JsonObject}
	 */
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
}
