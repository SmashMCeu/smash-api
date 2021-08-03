package eu.smashmc.api.identity.minecraft;

import java.util.UUID;

import eu.smashmc.api.identity.Identity;
import eu.smashmc.api.identity.minecraft.property.TexturesProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class MinecraftIdentity implements Identity<UUID> {

	private UUID uuid;

	@EqualsAndHashCode.Exclude
	private String name;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private TexturesProperty texture;

	@Override
	public UUID getId() {
		return uuid;
	}
}
