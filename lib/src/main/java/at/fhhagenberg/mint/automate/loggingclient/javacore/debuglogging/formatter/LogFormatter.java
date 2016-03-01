package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.formatter;

import java.util.Date;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Interface for the formatter that knows how to format a certain log message and priority.
 */
public interface LogFormatter {
	/**
	 * Format a priority, source string, message and time.
	 *
	 * @param priority -
	 * @param src      -
	 * @param msg      -
	 * @param time     -
	 * @return -
	 */
	String format(DebugLogManager.Priority priority, String src, String msg, Date time);

	/**
	 * Get the integer value for a priority.
	 *
	 * @param priority -
	 * @return -
	 */
	int formatPriority(DebugLogManager.Priority priority);
}
