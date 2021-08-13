package eu.smashmc.api.stats;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsPhase {

	public static StatsPhase alltime(String gameType) {
		return new StatsPhase(LocalDate.EPOCH, LocalDate.now(), gameType);
	}

	public static StatsPhase weekly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(7);
		return new StatsPhase(from, LocalDate.now(), gameType);
	}

	public static StatsPhase monthly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(30);
		return new StatsPhase(from, LocalDate.now(), gameType);
	}

	private LocalDate startDate;
	private LocalDate endDate;
	private String gameType;

}
