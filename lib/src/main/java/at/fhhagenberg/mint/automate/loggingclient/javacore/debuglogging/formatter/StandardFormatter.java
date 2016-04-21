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

import java.text.SimpleDateFormat;
import java.util.Date;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * A default formatter that just appends everything in a simple manner.
 */
@SuppressWarnings("unused")
public class StandardFormatter implements LogFormatter {
    /**
     * The date format for the date output.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * Convert a date to a string.
     *
     * @param time -
     * @return -
     */
    private static String timeToString(Date time) {
        return DATE_FORMAT.format(time);
    }

    @Override
    public String format(DebugLogManager.Priority priority, String src, String msg, Date time) {
        StringBuilder builder = new StringBuilder(timeToString(time)).append(" ").append(priority);
        if (src != null) {
            builder.append(" [").append(src).append("]");
        }
        builder.append(": ");

        if (msg != null) {
            builder.append(msg);
        }

        return builder.toString();
    }

    @Override
    public int formatPriority(DebugLogManager.Priority priority) {
        return 0;
    }
}
