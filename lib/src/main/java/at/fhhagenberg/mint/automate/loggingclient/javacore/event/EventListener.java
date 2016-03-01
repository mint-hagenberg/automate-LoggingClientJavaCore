package at.fhhagenberg.mint.automate.loggingclient.javacore.event;

/**
 * Event listener interface for the EventManager.
 */
public interface EventListener {
	/**
	 * Get notified about events.
	 *
	 * @param event -
	 */
	void handleEvent(Event event);
}
