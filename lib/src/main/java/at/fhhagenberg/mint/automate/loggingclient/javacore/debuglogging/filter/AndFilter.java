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
 * Combine two log filters and only accept when both return true.
 */
public class AndFilter implements LogFilter {
	private LogFilter mFilter1;
	private LogFilter mFilter2;

	public AndFilter(LogFilter filter1, LogFilter filter2) {
		mFilter1 = filter1;
		mFilter2 = filter2;
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, String msg) {
		return mFilter1.accept(priority, src, msg)
				&& mFilter2.accept(priority, src, msg);
	}

	@Override
	public boolean accept(DebugLogManager.Priority priority, String src, Object msg) {
		return mFilter1.accept(priority, src, msg)
				&& mFilter2.accept(priority, src, msg);
	}
}
