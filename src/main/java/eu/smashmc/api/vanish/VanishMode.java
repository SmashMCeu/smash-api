package eu.smashmc.api.vanish;

public enum VanishMode {
	NONE, UNDERCOVER, INVISIBLE, SPECTATE;

	public boolean isInvisibleToOthers() {
		return this == INVISIBLE;
	}

	public boolean shouldExcludeFromGameplay() {
		return this == UNDERCOVER || this == SPECTATE;
	}
}
