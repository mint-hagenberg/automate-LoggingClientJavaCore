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
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * Register as an event listener with the event manager.
 */
@SuppressWarnings("unused")
public class RegisterEventListenerAction implements Action {
    /**
     * The event manager that will register to.
     */
    private EventManager mEventManager;
    /**
     * Event types the listener is interested in.
     */
    private Id[] mTypes;
    /**
     * Event listener we want to register.
     */
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
