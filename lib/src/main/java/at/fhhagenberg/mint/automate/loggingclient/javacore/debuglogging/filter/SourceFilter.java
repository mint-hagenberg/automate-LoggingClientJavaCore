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
 * Only allow the log source specified.
 */
@SuppressWarnings("unused")
public class SourceFilter implements LogFilter {
    /**
     * The source name.
     */
    private String mSource;

    /**
     * Constructor.
     *
     * @param source -
     */
    public SourceFilter(String source) {
        mSource = source;
    }

    @Override
    public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
        return mSource.equals(src);
    }

    @Override
    public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
        return mSource.equals(src);
    }
}
