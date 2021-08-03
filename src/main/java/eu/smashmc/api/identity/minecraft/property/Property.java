package eu.smashmc.api.identity.minecraft.property;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Property {

	private String name;
	private String value;
	private String signature;

	public Property(String name, String value, String signature) {
		this.name = name;
		this.value = value;
		this.signature = signature;
	}

	public String getName() {
		return name;
	}

	public String getSignature() {
		return signature;
	}

	public String getValue() {
		return value;
	}
}
