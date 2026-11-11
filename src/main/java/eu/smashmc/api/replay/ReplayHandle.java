package eu.smashmc.api.replay;

/**
 * Identifier of a stored replay.
 *
 * @param id      the replay id
 * @param display the short display form, e.g. {@code "#7"}; never {@code null}
 */
public record ReplayHandle(long id, String display) {
}
