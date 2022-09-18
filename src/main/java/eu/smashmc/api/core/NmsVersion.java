package eu.smashmc.api.core;

import lombok.Getter;

public enum NmsVersion {
	MC_1_8_R3(), OTHER();

	@Getter
	static NmsVersion currentVersion;

	static {
		try {
			Class.forName("net.minecraft.server.v1_8_R3.EntityPlayer");
			currentVersion = MC_1_8_R3;
		} catch (Throwable t) {
			currentVersion = OTHER;
		}
	}
}
