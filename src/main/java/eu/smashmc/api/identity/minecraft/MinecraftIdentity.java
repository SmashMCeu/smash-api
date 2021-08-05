package eu.smashmc.api.identity.minecraft;

import java.util.UUID;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MinecraftIdentity implements Identity<UUID> {

	private final UUID uuid;

	private final String name;

	@ToString.Exclude
	private final TexturesProperty texture;

	public MinecraftIdentity(UUID uuid, String name) {
		this(uuid, name, null);
	}

	public MinecraftIdentity(UUID uuid, String name, TexturesProperty texture) {
		this.uuid = uuid;
		this.name = name;
		this.texture = texture;
	}

	@Override
	public UUID getId() {
		return uuid;
	}

	public boolean isTextureLoaded() {
		return texture != null;
	}

	public TexturesProperty getTexture() {
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
