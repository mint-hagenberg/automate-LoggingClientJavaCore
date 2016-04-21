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

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;
import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.formatter.LogFormatter;

/**
 * A basic implementation of the log listener that allows adding of filters.
 */
@SuppressWarnings("unused")
public abstract class BasicLogger implements LogListener {
    /**
     * The used log formatter.
     */
    private LogFormatter mFormatter;
    /**
     * A list of applied filter.
     */
    private List<LogFilter> mFilters = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param filter    -
     * @param formatter -
     */
    public BasicLogger(LogFilter filter, LogFormatter formatter) {
        if (filter != null) {
            addFilter(filter);
        }

        mFormatter = formatter;
    }

    /**
     * Return all the registered filters.
     *
     * @return -
     */
    public List<LogFilter> getFilters() {
        return mFilters;
    }

    /**
     * Add a filter.
     *
     * @param filter -
     */
    public final void addFilter(LogFilter filter) {
        mFilters.add(filter);
    }

    /**
     * Remove a filter.
     *
     * @param filter -
     */
    public final void removeFilter(LogFilter filter) {
        mFilters.remove(filter);
    }

    /**
     * Remove all filters.
     */
    public final void removeAllFilters() {
        mFilters.clear();
    }

    /**
     * Get the set formatter.
     *
     * @return -
     */
    public LogFormatter getFormatter() {
        return mFormatter;
    }
}
