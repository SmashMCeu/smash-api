package eu.smashmc.api.quest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Difficulty {
	EASY("Einfach"),
	NORMAL("Normal"),
	HARD("Schwer");

	private final String displayName;
}
