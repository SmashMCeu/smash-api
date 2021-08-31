package eu.smashmc.api.stats;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class StatsPeriod {

	public static StatsPeriod alltime(String gameType) {
		return new StatsPeriod(gameType, LocalDate.EPOCH, LocalDate.now());
	}

	public static StatsPeriod weekly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(7);
		return new StatsPeriod(gameType, from, LocalDate.now());
	}

	public static StatsPeriod monthly(String gameType) {
		LocalDate from = LocalDate.now().minusDays(30);
		return new StatsPeriod(gameType, from, LocalDate.now());
	}

	@NonNull
	private String gameType;
	@NonNull
	private LocalDate startDate;
	@NonNull
	private LocalDate endDate;

}
