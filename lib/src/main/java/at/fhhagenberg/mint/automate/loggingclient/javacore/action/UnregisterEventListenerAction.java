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

package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventListener;
import at.fhhagenberg.mint.automate.loggingclient.javacore.event.EventManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Unregister from an event with the event manager.
 */
@SuppressWarnings("unused")
public class UnregisterEventListenerAction implements Action {
    /**
     * Event manager that will be used to unregister.
     */
    private EventManager mEventManager;
    /**
     * Remove this listener from the event manager.
     */
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
