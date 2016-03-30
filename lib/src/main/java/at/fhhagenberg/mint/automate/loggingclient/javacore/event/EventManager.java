/*
 *     Copyright (C) 2016 Mobile Interactive Systems Research Group
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.fhhagenberg.mint.automate.loggingclient.javacore.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.KernelListener;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.ManagerException;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.UpdateableManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;
import at.fhhagenberg.mint.automate.loggingclient.javacore.util.Bag;

/**
 * The Event Manager receives Events from external sources and dispatches them to all relevant registered listeners.
 */
public class EventManager extends UpdateableManager implements KernelListener {
	public static final Id ID = new Id(EventManager.class);

	private Map<Id, Bag<EventListener>> mListenerMap = new HashMap<>();
	private List<Event> mEvents1 = new ArrayList<>();
	private List<Event> mEvents2 = new ArrayList<>();
	private List<Event> mEurrentEvents = mEvents1;
	private List<TimedEvent> mTimedEvents = new ArrayList<TimedEvent>();

	public EventManager() {
		addDependency(DebugLogManager.ID);
	}

	@Override
	protected void doStart() throws ManagerException {
		super.doStart();

		getKernel().addListener(this);
	}

	@Override
	protected void doStop() {
		for (Event event : mEvents1) {
			event.release();
		}
		mEvents1.clear();

		for (Event event : mEvents2) {
			event.release();
		}
		mEvents2.clear();

		int numListeners = 0;
		for (Bag<EventListener> listeners : mListenerMap.values()) {
			numListeners += listeners.size();
		}
		if (numListeners > 0) {
			getLogger().logWarning(getLoggingSource(), "num of listeners = " + numListeners);
			for (Bag<EventListener> listeners : mListenerMap.values()) {
				for (listeners.reset(); listeners.hasNext(); ) {
					getLogger().logDebug(getLoggingSource(), "listener " + listeners.next().getClass().getName() + " was not removed");
				}
			}
		}

		mListenerMap.clear();
		getKernel().removeListener(this);

		super.doStop();
	}

	@Override
	public void update() {
		fireTimedEvents();

		do {
			List<Event> events = mEurrentEvents;
			swapEventList();
			for (Event event : events) {
				fireEvent(event);
				event.release();
			}
			events.clear();
		} while (!this.mEurrentEvents.isEmpty());
	}

	private void fireTimedEvents() {
		for (Iterator<TimedEvent> it = mTimedEvents.iterator(); it.hasNext(); ) {
			TimedEvent timedEvent = it.next();
			if (timedEvent.getTime() <= getKernel().getTime()) {
				dispatchEvent(timedEvent.getEvent());
				it.remove();
			}
		}
	}

	/**
	 * Add an event to the dispatch queue. Defaults to fast (=immediate) dispatching.
	 *
	 * @param event -
	 */
	public void dispatchEvent(Event event) {
		fireEvent(event);
		event.release();
		// TODO: do we want to support slow events?!
		//} else {
		//	this.mEurrentEvents.add(event);
		//}
	}

	/**
	 * Add a timed event to the deferred dispatch queue.
	 *
	 * @param event -
	 * @param delay -
	 */
	public void dispatchEvent(Event event, double delay) {
		if (delay <= 0) {
			throw new IllegalArgumentException("delay must be greater than zero");
		}

		addTimedEvent(new TimedEvent(event, getKernel().getTime() + delay));
	}

	private void addTimedEvent(TimedEvent timedEvent) {
		int size = mTimedEvents.size();
		for (int i = 0; i < size; ++i) {
			if (mTimedEvents.get(i).getTime() > timedEvent.getTime()) {
				mTimedEvents.add(i, timedEvent);
				return;
			}
		}
		mTimedEvents.add(timedEvent);
	}

	/**
	 * Add an event listener for a certain type of event.
	 *
	 * @param listener -
	 * @param typeId   -
	 */
	public void addListener(EventListener listener, Id typeId) {
		Bag<EventListener> listeners = mListenerMap.get(typeId);

		if (listeners == null) {
			listeners = new Bag<>();
			mListenerMap.put(typeId, listeners);
			listeners.add(listener);
		} else if (!listeners.contains(listener)) {
			listeners.add(listener);
		} else {
			getLogger().logWarning(
					getLoggingSource(),
					"attempt to add listener for event type " + typeId + " twice (" + listener.getClass().getName() + ")");
		}
	}

	/**
	 * Remove an event listener for a certain event class.
	 *
	 * @param listener -
	 * @param typeId   -
	 */
	public void removeListener(EventListener listener, Class<?> typeId) {
		removeListener(listener, new Id(typeId));
	}

	/**
	 * Remove an event listener for a certain event type.
	 *
	 * @param listener -
	 * @param typeId   -
	 */
	public void removeListener(EventListener listener, Id typeId) {
		Bag<EventListener> listeners = mListenerMap.get(typeId);

		if (listeners == null || !listeners.remove(listener)) {
			getLogger().logWarning(getLoggingSource(),
					"attempt to remove unregistered listener for event type " + typeId + " (" + listener.getClass().getName() + ")");
		}
	}

	/**
	 * Remove an event listener for all events it's registered for.
	 *
	 * @param listener -
	 */
	public void removeListener(EventListener listener) {
		for (Bag<EventListener> bag : mListenerMap.values()) {
			bag.remove(listener);
		}
	}

	/**
	 * Check if an event listener was registered for a certain type of event.
	 *
	 * @param listener -
	 * @param typeId   -
	 * @return -
	 */
	public boolean hasListener(EventListener listener, Id typeId) {
		Bag<EventListener> listeners = mListenerMap.get(typeId);
		return listeners != null && listeners.contains(listener);
	}

	private void swapEventList() {
		if (mEurrentEvents == mEvents1) {
			mEurrentEvents = mEvents2;
		} else {
			mEurrentEvents = mEvents1;
		}
	}

	private void fireEvent(Event event) {
		Bag<EventListener> listeners = mListenerMap.get(event.getTypeId());
		if (listeners == null) {
			// no listeners for this event
			return;
		}

		for (listeners.reset(); listeners.hasNext(); ) {
			listeners.next().handleEvent(event);
		}
	}

	@Override
	public void startupFinished() {
	}

	@Override
	public void onPrepareShutdown() {
	}

	@Override
	public void onShutdown() {
		update();
	}

	@Override
	public Id getId() {
		return ID;
	}

	/**
	 * A timed event that will only fire after a certain time.
	 */
	private static class TimedEvent {
		private Event mEvent;
		private double mTime;

		public TimedEvent(Event event, double time) {
			mEvent = event;
			mTime = time;
		}

		public Event getEvent() {
			return mEvent;
		}

		public double getTime() {
			return mTime;
		}
	}
}
