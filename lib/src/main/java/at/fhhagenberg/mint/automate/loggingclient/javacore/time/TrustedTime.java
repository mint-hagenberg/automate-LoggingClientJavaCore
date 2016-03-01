package at.fhhagenberg.mint.automate.loggingclient.javacore.time;

/**
 * Interface that provides trusted time information, possibly coming from an NTP
 * server. Implementations may cache answers until {@link #forceRefresh()}.
 */
// @hide
public interface TrustedTime {
	/**
	 * Force update with an external trusted time source, returning {@code true} when successful.
	 *
	 * @return true when refresh was successful
	 */
	boolean forceRefresh();

	/**
	 * Check if this instance has cached a response from a trusted time source.
	 *
	 * @return true if has cache
	 */
	boolean hasCache();

	/**
	 * Return time since last trusted time source contact, or {@link Long#MAX_VALUE} if never contacted.
	 *
	 * @return cache age in millis
	 */
	long getCacheAge();

	/**
	 * Return certainty of cached trusted time in milliseconds, or
	 * {@link Long#MAX_VALUE} if never contacted. Smaller values are more precise.
	 *
	 * @return cached time certainty in millis
	 */
	long getCacheCertainty();

	/**
	 * Return current time similar to {@link System#currentTimeMillis()},
	 * possibly using a cached authoritative time source.
	 *
	 * @return current time in millis
	 */
	long currentTimeMillis();

	/**
	 * Return current millisecond timezone offset for local time.
	 *
	 * @return current timezone offset in millis
	 */
	int currentTimeZoneOffsetMillis();
}