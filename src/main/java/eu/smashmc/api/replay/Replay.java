package eu.smashmc.api.replay;

import eu.smashmc.api.Environment;
import eu.smashmc.api.SmashComponent;

@SmashComponent(value = Environment.BUKKIT, fallbackImpl = FallbackReplay.class)
public interface Replay {

	ReplaySession startRecording(ReplayConfig config);

	void stopRecording(ReplaySession session);

	boolean isRecording();
}
