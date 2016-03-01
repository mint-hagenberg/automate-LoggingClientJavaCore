package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * A default formatter that just appends everything in a simple manner.
 */
public class StandardFormatter implements LogFormatter {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private String timeToString(Date time) {
		return DATE_FORMAT.format(time);
	}

	@Override
	public String format(DebugLogManager.Priority priority, String src, String msg, Date time) {
		StringBuilder builder = new StringBuilder(timeToString(time))
				.append(" " + priority);
		if (src != null) {
			builder.append(" [" + src + "]");
		}
		builder.append(": ");

		if (msg != null) {
			builder.append(msg);
		}

		return builder.toString();
	}

	@Override
	public int formatPriority(DebugLogManager.Priority priority) {
		return 0;
	}
}
