package eu.smashmc.api.quest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Difficulty {
	EASY("lore.quest.difficulty.easy"),
	NORMAL("lore.quest.difficulty.normal"),
	HARD("lore.quest.difficulty.hard"),;

	private final String displayName;
}
