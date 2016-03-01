package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Invert the filter.
 */
public class NotFilter implements LogFilter {
	private LogFilter mFilter;

	public NotFilter(LogFilter filter) {
		this.mFilter = filter;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return !this.mFilter.accept(priority, src, msg);
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return !this.mFilter.accept(priority, src, msg);
	}
}
