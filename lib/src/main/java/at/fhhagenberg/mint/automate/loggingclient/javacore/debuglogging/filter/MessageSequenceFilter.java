package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;
import at.fhooe.automate.logger.base.logging.LoggingService.Priority;

public class MessageSequenceFilter implements LogFilter {
	private String msgSequence;

	public MessageSequenceFilter(String messageSequence) {
		this.msgSequence = messageSequence;
	}

	@Override
	public boolean accept(Priority priority, String src, String msg) {
		return msg.contains(msgSequence);
	}

	@Override
	public boolean accept(Priority priority, String src, Object msg) {
		return false;
	}
}
