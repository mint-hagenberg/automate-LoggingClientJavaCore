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

package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging;

import java.util.List;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;

/**
 * Listener interface for the logs.
 */
public interface LogListener {
    /**
     * Get all the associated filters.
     *
     * @return -
     */
    List<LogFilter> getFilters();

    /**
     * Logging was started for this listener.
     *
     * @param instance -
     */
    void doStart(DebugLogManager instance);

    /**
     * Output a log message with the given priority, source and message.
     *
     * @param priority -
     * @param source   -
     * @param message  -
     */
    void doLogMessage(DebugLogManager.Priority priority, String source,
                      String message);

    /**
     * Output a log message with the given priority, source and message object.
     *
     * @param priority -
     * @param source   -
     * @param message  -
     */
    void doLogMessage(DebugLogManager.Priority priority, String source,
                      Object message);

    /**
     * Output a log message with the given priority, source and throwable.
     *
     * @param critical  -
     * @param source    -
     * @param throwable -
     */
    void doLogMessage(DebugLogManager.Priority critical, String source,
                      Throwable throwable);

    /**
     * Logging is being stopped for this listener.
     *
     * @param instance -
     */
    void doStop(DebugLogManager instance);
}
