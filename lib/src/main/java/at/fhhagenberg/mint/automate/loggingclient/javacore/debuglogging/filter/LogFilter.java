package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Interface to allow filtering of log messages based on their priority or other factors.
 */
public interface LogFilter {
	/**
	 * Filter based on priority, source and message combination.
	 *
	 * @param priority -
	 * @param src      -
	 * @param msg      -
	 * @return -
	 */
	boolean accept(DebugLogManager.Priority priority, String src, String msg);

	/**
	 * Filter based on priority, source and message object combination.
	 *
	 * @param priority -
	 * @param src      -
	 * @param msg      -
	 * @return -
	 */
	boolean accept(DebugLogManager.Priority priority, String src, Object msg);
}
