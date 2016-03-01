package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Only allow the log source specified.
 */
public class SourceFilter implements LogFilter {
	private String mSource;

	public SourceFilter(String source) {
		mSource = source;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return mSource.equals(src);
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return mSource.equals(src);
	}
}
