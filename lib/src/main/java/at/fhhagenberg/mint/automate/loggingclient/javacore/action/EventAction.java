package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.event.Event;
import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.event.SimpleEvent;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * Send an event to the event manager for dispatching.
 */
public class EventAction implements Action {
	private EventManager mEventManager;
	private Event mEvent;

	/**
	 * Dispatch a specific event.
	 *
	 * @param kernel -
	 * @param event  -
	 */
	public EventAction(Kernel kernel, Event event) {
		mEventManager = AbstractManager.getInstance(kernel, EventManager.class);
		mEvent = event;
	}

	/**
	 * Dispatch a simple event with just a type and without custom arguments.
	 *
	 * @param core        -
	 * @param eventTypeId -
	 */
	public EventAction(Kernel core, Id eventTypeId) {
		this(core, new SimpleEvent(eventTypeId));
	}

	@Override
	public void execute() {
		mEventManager.dispatchEvent(mEvent);
	}
}
