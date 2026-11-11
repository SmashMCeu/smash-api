package eu.smashmc.api.replay;

/**
 * Identifier of one stored replay.
 * <p>
 * A handle stays valid after the recording is uploaded and can be stored by
 * callers. It is not the same as {@link ReplaySession#id()}.
 *
 * @param id      the numeric replay id
 * @param display the short display form shown to staff, e.g. {@code "#7"};
 *                never {@code null}
 */
public record ReplayHandle(long id, String display) {
}
