package eu.smashmc.api.identity.minecraft.property;

import java.util.Base64;

import com.google.gson.Gson;

public class TexturesProperty extends Property {

	public TexturesProperty(String value, String signature) {
		super("textures", value, signature);
	}

	@Override
	public String getName() {
		return "textures";
	}

	public TexturesPropertyValue decodeValue() {
		byte[] data = Base64.getDecoder()
				.decode(this.getValue());
		String json = new String(data);
		Gson gson = new Gson();
		TexturesPropertyValue value = gson.fromJson(json, TexturesPropertyValue.class);
		return value;
	}
}
