package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Combine two log filters and only accept when both return true.
 */
public class AndFilter implements LogFilter {
	private LogFilter mFilter1;
	private LogFilter mFilter2;

	public AndFilter(LogFilter filter1, LogFilter filter2) {
		mFilter1 = filter1;
		mFilter2 = filter2;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return mFilter1.accept(priority, src, msg)
				&& mFilter2.accept(priority, src, msg);
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return mFilter1.accept(priority, src, msg)
				&& mFilter2.accept(priority, src, msg);
	}
}
