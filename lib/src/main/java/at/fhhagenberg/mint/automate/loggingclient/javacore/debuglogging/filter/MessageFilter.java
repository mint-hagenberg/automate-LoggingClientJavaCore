package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhooe.automate.logger.base.logging.LoggingService.Priority;

public class MessageFilter implements LogFilter {
	private Object message;

	public MessageFilter(String message) {
		this.message = message;
	}

	@Override
	public boolean accept(Priority priority, String src, String msg) {
		return this.message.equals(msg);
	}

	@Override
	public boolean accept(Priority priority, String src, Object msg) {
		return this.message.equals(msg);
	}
}
