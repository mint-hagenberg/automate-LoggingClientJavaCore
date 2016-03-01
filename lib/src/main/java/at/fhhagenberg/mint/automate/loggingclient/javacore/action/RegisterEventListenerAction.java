package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventListener;
import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * Register as an event listener with the event manager.
 */
public class RegisterEventListenerAction implements Action {
	private EventManager mEventManager;
	private Id[] mTypes;
	private EventListener mListener;

	/**
	 * Register.
	 *
	 * @param kernel   -
	 * @param listener -
	 * @param types    -
	 */
	public RegisterEventListenerAction(Kernel kernel, EventListener listener, Id... types) {
		mEventManager = AbstractManager.getInstance(kernel, EventManager.class);
		mListener = listener;
		mTypes = types;
	}

	@Override
	public void execute() {
		for (Id mType : mTypes) {
			mEventManager.addListener(mListener, mType);
		}
	}
}
