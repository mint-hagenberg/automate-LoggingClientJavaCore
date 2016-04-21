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

package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.formatter;

import java.util.Date;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Interface for the formatter that knows how to format a certain log message and priority.
 */
@SuppressWarnings("unused")
public interface LogFormatter {
    /**
     * Format a priority, source string, message and time.
     *
     * @param priority -
     * @param src      -
     * @param msg      -
     * @param time     -
     * @return -
     */
    String format(DebugLogManager.Priority priority, String src, String msg, Date time);

    /**
     * Get the integer value for a priority.
     *
     * @param priority -
     * @return -
     */
    int formatPriority(DebugLogManager.Priority priority);
}
