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

package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Interface to allow filtering of log messages based on their priority or other factors.
 */
@SuppressWarnings("unused")
public interface LogFilter {
    /**
     * Filter based on priority, source and message combination.
     *
     * @param priority -
     * @param src      -
     * @param msg      -
     * @return -
     */
    boolean accept(DebugLogManager.Priority priority, String src, String msg);

    /**
     * Filter based on priority, source and message object combination.
     *
     * @param priority -
     * @param src      -
     * @param msg      -
     * @return -
     */
    boolean accept(DebugLogManager.Priority priority, String src, Object msg);
}
