package eu.smashmc.api.identity.minecraft.property;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(exclude = { "textures" })
public class TexturesPropertyValue {
	private long timestamp;
	private String profileId;
	private String profileName;
	private boolean signatureRequired;
	private Textures textures;

	@ToString
	@EqualsAndHashCode
	public static class Textures {
		private Texture SKIN;
		private Texture CAPE;

		public Texture getSkin() {
			return SKIN;
		}

		public Texture getCape() {
			return CAPE;
		}
	}

	@Getter
	@ToString
	@EqualsAndHashCode
	public static class Texture {
		private String url;
	}
}
