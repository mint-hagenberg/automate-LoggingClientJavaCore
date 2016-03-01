package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Just allow any kind of log message.
 */
public class PassThroughFilter implements LogFilter {
	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return true;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return true;
	}
}
