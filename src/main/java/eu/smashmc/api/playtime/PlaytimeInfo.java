package eu.smashmc.api.playtime;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface PlaytimeInfo {
	public long getPlaytime(TimeUnit unit);

	public LocalDateTime getLastSeen();

	public LocalDateTime getFirstSeen();

	public UUID getUuid();

	public String getName();
}
