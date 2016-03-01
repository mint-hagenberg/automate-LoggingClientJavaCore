package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Combine multiple filters with an or, so if any of the filters returns true, the message will be accepted.
 */
public class OrFilter implements LogFilter {
	private LogFilter[] mFilters;

	public OrFilter(LogFilter... filter) {
		mFilters = filter;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {

		for (LogFilter f : mFilters) {
			if (f.accept(priority, src, msg)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		for (LogFilter f : mFilters) {
			if (f.accept(priority, src, msg)) {
				return true;
			}
		}

		return false;
	}
}
