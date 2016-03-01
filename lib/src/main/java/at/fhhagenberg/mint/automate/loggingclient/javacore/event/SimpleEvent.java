package at.fhhagenberg.mint.automate.loggingclient.javacore.event;

import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * A simple Event which already provides an Id as a type.
 */
public class SimpleEvent extends Event {
	private Id mTypeId;

	/**
	 * Constrcutor for custom types.
	 *
	 * @param typeId -
	 */
	public SimpleEvent(Id typeId) {
		mTypeId = typeId;
	}

	@Override
	public Id getTypeId() {
		return mTypeId;
	}
}
