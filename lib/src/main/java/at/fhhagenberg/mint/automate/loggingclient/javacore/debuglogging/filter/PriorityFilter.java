package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Only allow the priority as specified.
 */
public class PriorityFilter implements LogFilter {
	private DebugLogManager.Priority mPriority;

	public PriorityFilter(DebugLogManager.Priority priority) {
		this.mPriority = priority;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return mPriority == priority;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return mPriority == priority;
	}
}
