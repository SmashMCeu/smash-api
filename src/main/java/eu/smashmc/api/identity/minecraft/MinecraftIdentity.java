package eu.smashmc.api.identity.minecraft;

import java.util.UUID;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * Identity representing some Minecraft user with an {@link UUID}, a name and a
 * {@link TexturesProperty}.
 * 
 * @author LiquidDev
 */
@Getter
@ToString
public class MinecraftIdentity implements Identity<UUID> {

	private final UUID uuid;

	private final String name;

	@ToString.Exclude
	private final TexturesProperty texture;

	/**
	 * Create an identity reference.
	 * 
	 * @param uuid    the identities {@link UUID}
	 * @param name    the identities name
	 * @param texture the identities {@link TexturesProperty}
	 */
	public MinecraftIdentity(UUID uuid, String name, TexturesProperty texture) {
		this.uuid = uuid;
		this.name = name;
		this.texture = texture;
	}

	/**
	 * Create an identity reference without a {@link TexturesProperty} loaded.
	 * 
	 * @param uuid the identities {@link UUID}
	 * @param name the identities name
	 */
	public MinecraftIdentity(UUID uuid, String name) {
		this(uuid, name, null);
	}

	/**
	 * Returns the identities {@link UUID}.
	 * 
	 * @return the identities {@link UUID}
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Returns the identities {@link UUID}. Same as
	 * {@link MinecraftIdentity#getUuid()}.
	 * 
	 * @return the identities {@link UUID}
	 */
	@Override
	public UUID getId() {
		return uuid;
	}

	/**
	 * Checks if the {@link TexturesProperty} of the identity is loaded.
	 * 
	 * @return <code>true</code> if texture is loaded
	 */
	public boolean isTextureLoaded() {
		return texture != null;
	}

	/**
	 * Returns the identities {@link TexturesProperty} if loaded, otherwise throws
	 * {@link IllegalStateException}.
	 * 
	 * @return the {@link TexturesProperty}
	 * @throws IllegalStateException if texture not loaded
	 */
	public TexturesProperty getTexture() throws IllegalStateException {
		if (!isTextureLoaded()) {
			throw new IllegalStateException("texture not loaded");
		}
		return texture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MinecraftIdentity other = (MinecraftIdentity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}
