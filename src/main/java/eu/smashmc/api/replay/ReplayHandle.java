package eu.smashmc.api.replay;

/**
 * Stable, user-facing identifier of one stored replay.
 * <p>
 * The handle is assigned by the {@code replay-service} and is independent of
 * the local {@link ReplaySession#id()}: it survives upload, playback and the
 * lobby hand-off, so consumers (e.g. the reports and punishments plugins) can
 * store it as a durable reference. The numeric part is the backend id; the
 * display form is the canonical {@code "#<id>"} shown to staff.
 *
 * @param id      the backend replay id
 * @param display the canonical short display form, i.e. {@code "#<id>"}
 */
public record ReplayHandle(long id, String display) {
}
