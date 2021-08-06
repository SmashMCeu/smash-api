package eu.smashmc.api.identity.minecraft.property;

import com.google.common.base.Preconditions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Property {

	private String name;
	private String value;
	private String signature;

	public Property(String name, String value, String signature) {
		Preconditions.checkNotNull(name, "name cannot be null");
		Preconditions.checkNotNull(value, "value cannot be null");
		Preconditions.checkNotNull(signature, "signature cannot be null");
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
