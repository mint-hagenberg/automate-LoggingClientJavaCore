package at.fhhagenberg.mint.automate.loggingclient.javacore.event;

import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;
import at.fhhagenberg.mint.automate.loggingclient.javacore.util.ReflectionHelper;

/**
 * The abstract base class for all Events which can be sent around via the EventManager.
 */
public abstract class Event {
	/**
	 * Return the event type id.
	 *
	 * @return -
	 */
	public abstract Id getTypeId();

	/**
	 * Release the event.
	 */
	public void release() {
		// intentionally left empty
	}

	/**
	 * Check if this event is of a certain type.
	 *
	 * @param id -
	 * @return -
	 */
	public final boolean isOfType(Id id) {
		return getTypeId().equals(id);
	}

	/**
	 * Check if this event is of a certain class type.
	 *
	 * @param clazz -
	 * @return -
	 */
	public final boolean isOfType(Class<?> clazz) {
		return ReflectionHelper.isExtendingClass(this.getClass(), clazz);
	}
}
