package eu.smashmc.api.identity.minecraft;

import java.util.UUID;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class MinecraftIdentity implements Identity<UUID> {

	private final UUID uuid;

	@EqualsAndHashCode.Exclude
	private final String name;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
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
}
