package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventListener;
import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Unregister from an event with the event manager.
 */
public class UnregisterEventListenerAction implements Action {
	private EventManager mEventManager;
	private EventListener mListener;

	/**
	 * Unregister.
	 *
	 * @param kernel   -
	 * @param listener -
	 */
	public UnregisterEventListenerAction(Kernel kernel, EventListener listener) {
		mEventManager = AbstractManager.getInstance(kernel, EventManager.class);
		mListener = listener;
	}

	@Override
	public void execute() {
		mEventManager.removeListener(mListener);
	}
}
