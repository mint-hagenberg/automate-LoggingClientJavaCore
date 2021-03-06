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

package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;

/**
 * Combine multiple filters with an or, so if any of the filters returns true, the message will be accepted.
 */
@SuppressWarnings("unused")
public class OrFilter implements LogFilter {
    /**
     * A number of filters to compine.
     */
    private LogFilter[] mFilters;

    /**
     * Constructor.
     *
     * @param filter -
     */
    public OrFilter(LogFilter... filter) {
        mFilters = filter;
    }

    @Override
    public boolean accept(DebugLogManager.Priority priority, String src, String msg) {

        for (LogFilter f : mFilters) {
            if (f.accept(priority, src, msg)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
        for (LogFilter f : mFilters) {
            if (f.accept(priority, src, msg)) {
                return true;
            }
        }

        return false;
    }
}
