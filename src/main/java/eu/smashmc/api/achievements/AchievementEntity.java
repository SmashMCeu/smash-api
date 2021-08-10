package eu.smashmc.api.achievements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchievementEntity {
    private String key;
    private String type;
    private int difficulty;
    private String description;
}
