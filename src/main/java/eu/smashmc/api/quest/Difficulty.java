package eu.smashmc.api.quest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Difficulty {
	EASY("lang:lore.quest.difficulty.easy"),
	NORMAL("lang:lore.quest.difficulty.normal"),
	HARD("lang:lore.quest.difficulty.hard"),;

	private final String displayName;
}
