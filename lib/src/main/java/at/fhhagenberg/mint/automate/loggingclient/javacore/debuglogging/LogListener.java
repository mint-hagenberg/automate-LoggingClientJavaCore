package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging;

import java.util.List;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;

/**
 * Listener interface for the logs.
 */
public interface LogListener {
	/**
	 * Get all the associated filters.
	 *
	 * @return -
	 */
	List<LogFilter> getFilters();

	/**
	 * Logging was started for this listener.
	 *
	 * @param instance -
	 */
	void doStart(DebugLogManager instance);

	/**
	 * Output a log message with the given priority, source and message.
	 *
	 * @param priority -
	 * @param source   -
	 * @param message  -
	 */
	void doLogMessage(DebugLogManager.Priority priority, String source,
					  String message);

	/**
	 * Output a log message with the given priority, source and message object.
	 *
	 * @param priority -
	 * @param source   -
	 * @param message  -
	 */
	void doLogMessage(DebugLogManager.Priority priority, String source,
					  Object message);

	/**
	 * Output a log message with the given priority, source and throwable.
	 *
	 * @param critical  -
	 * @param source    -
	 * @param throwable -
	 */
	void doLogMessage(DebugLogManager.Priority critical, String source,
					  Throwable throwable);

	/**
	 * Logging is being stopped for this listener.
	 *
	 * @param instance -
	 */
	void doStop(DebugLogManager instance);
}
