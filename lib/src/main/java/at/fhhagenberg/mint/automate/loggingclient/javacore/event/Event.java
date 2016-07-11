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

package at.fhhagenberg.mint.automate.loggingclient.javacore.event;

import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;
import at.fhhagenberg.mint.automate.loggingclient.javacore.util.ReflectionHelper;

/**
 * The abstract base class for all Events which can be sent around via the EventManager.
 */
@SuppressWarnings("unused")
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
