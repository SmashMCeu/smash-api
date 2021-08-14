package eu.smashmc.api.stats;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class StatsPhase {

	public static StatsPhase alltime(String gameType) {
		return new StatsPhase(gameType, LocalDate.EPOCH, LocalDate.now());
	}

	public static StatsPhase weekly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(7);
		return new StatsPhase(gameType, from, LocalDate.now());
	}

	public static StatsPhase monthly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(30);
		return new StatsPhase(gameType, from, LocalDate.now());
	}

	@NonNull
	private String gameType;
	@NonNull
	private LocalDate startDate;
	@NonNull
	private LocalDate endDate;

}
