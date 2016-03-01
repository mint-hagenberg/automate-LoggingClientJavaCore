package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhooe.automate.logger.base.logging.LoggingService.Priority;

public class LevelFilter implements LogFilter {
	private DebugLogManager.Priority priority;

	public LevelFilter(DebugLogManager.Priority priority) {
		this.priority = priority;
	}

	@Override
	public boolean accept(Priority priority, String src, String msg) {
		return this.priority.compareTo(priority) <= 0;
	}

	@Override
	public boolean accept(Priority priority, String src, Object msg) {
		return this.priority.compareTo(priority) <= 0;
	}
}
