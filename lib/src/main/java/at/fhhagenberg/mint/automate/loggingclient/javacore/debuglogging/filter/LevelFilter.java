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
import at.fhooe.automate.logger.base.logging.LoggingService.Priority;

public class LevelFilter implements LogFilter {
	private DebugLogManager.Priority priority;

	public LevelFilter(DebugLogManager.Priority priority) {
		this.priority = priority;
	}

	@Override
	public boolean accept(Priority priority, String src, String msg) {
		return this.priority.compareTo(priority) <= 0;
	}

	@Override
	public boolean accept(Priority priority, String src, Object msg) {
		return this.priority.compareTo(priority) <= 0;
	}
}
