/*
 *     Copyright (C) 2016 Research Group Mobile Interactive Systems
 *     Email: mint@fh-hagenberg.at, Website: http://mint.fh-hagenberg.at
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
@SuppressWarnings("unused")
public class EventAction implements Action {
    /**
     * Event manager that the event is sent to.
     */
    private EventManager mEventManager;
    /**
     * The event that should be dispatched.
     */
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
