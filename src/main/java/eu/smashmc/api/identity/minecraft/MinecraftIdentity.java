package eu.smashmc.api.identity.minecraft;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Identity representing some Minecraft user with an {@link UUID}, a name and a
 * {@link TexturesProperty}.
 *
 * @author LiquidDev
 */
public interface MinecraftIdentity extends Identity<UUID> {

	/**
	 * Returns the identities {@link UUID}.
	 *
	 * @return the identities {@link UUID}
	 */
	UUID getUuid();

	/**
	 * Returns the identities {@link UUID}. Same as
	 * {@link MinecraftIdentity#getUuid()}.
	 *
	 * @return the identities {@link UUID}
	 */
	UUID getId();

	/**
	 * Returns the identities {@link TexturesProperty} if loaded or
	 * <code>null</code>.
	 *
	 * @return {@link TexturesProperty} or <code>null</code>
	 */
	@Nullable
	TexturesProperty getTextureOrNull();

	/**
	 * Returns the identities {@link TexturesProperty} if loaded, otherwise throws
	 * {@link IllegalStateException}.
	 *
	 * @return the {@link TexturesProperty}
	 * @throws IllegalStateException if texture not loaded
	 */
	default TexturesProperty getTexture() throws IllegalStateException {
		var texture = getTextureOrNull();
		if (texture == null) {
			throw new IllegalStateException("texture not loaded");
		}
		return texture;
	}

	/**
	 * Checks if the {@link TexturesProperty} of the identity is loaded.
	 *
	 * @return <code>true</code> if texture is loaded
	 */
	default boolean isTextureLoaded() {
		return getTextureOrNull() != null;
	}

	/**
	 * Returns the current server of a user, or <code>null</code> if offline.
	 *
	 * @return server name or <code>null</code>
	 */
	@Nullable
	String getServer();

	/**
	 * Checks if the user is online.
	 *
	 * @return <code>true</code> if user is online
	 */
	default boolean isOnline() {
		return getServer() != null;
	}
}
