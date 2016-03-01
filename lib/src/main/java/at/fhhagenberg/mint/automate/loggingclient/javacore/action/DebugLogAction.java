package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Log something using the DebugLogManager.
 */
public class DebugLogAction implements Action {
	private DebugLogManager mLogger;
	private String mSource;
	private String mMessage;
	private DebugLogManager.Priority mPriority;

	public DebugLogAction(Kernel kernel, DebugLogManager.Priority priority, String source, String message) {
		mLogger = AbstractManager.getInstance(kernel, DebugLogManager.class);
		mSource = source;
		mMessage = message;
		mPriority = priority;
	}

	@Override
	public void execute() {
		mLogger.log(mPriority, mSource, mMessage);
	}
}
