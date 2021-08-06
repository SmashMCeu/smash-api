package eu.smashmc.api.identity.minecraft;

import java.util.UUID;

import javax.annotation.Nullable;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;

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
	public UUID getUuid();

	/**
	 * Returns the identities {@link UUID}. Same as
	 * {@link MinecraftIdentity#getUuid()}.
	 * 
	 * @return the identities {@link UUID}
	 */
	public UUID getId();

	/**
	 * Checks if the {@link TexturesProperty} of the identity is loaded.
	 * 
	 * @return <code>true</code> if texture is loaded
	 */
	public boolean isTextureLoaded();

	/**
	 * Returns the identities {@link TexturesProperty} if loaded, otherwise throws
	 * {@link IllegalStateException}.
	 * 
	 * @return the {@link TexturesProperty}
	 * @throws IllegalStateException if texture not loaded
	 */
	public TexturesProperty getTexture() throws IllegalStateException;

	/**
	 * Returns the identities {@link TexturesProperty} if loaded or
	 * <code>null</code>.
	 * 
	 * @return {@link TexturesProperty} or <code>null</code>
	 */
	@Nullable
	public TexturesProperty getTextureOrNull();

	/**
	 * Checks if the user is online.
	 * 
	 * @return <code>true</code> if user is online
	 */
	public boolean isOnline();

	/**
	 * Returns the current server of a user, or <code>null</code> if offline.
	 * 
	 * @return server name or <code>null</code>
	 */
	@Nullable
	public String getServer();
}
