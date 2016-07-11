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

package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Log something using the DebugLogManager.
 */
@SuppressWarnings("unused")
public class DebugLogAction implements Action {
    /**
     * Logger manager that is used to output write to.
     */
    private DebugLogManager mLogger;
    /**
     * A source name that will be used for the log message.
     */
    private String mSource;
    /**
     * The message itself.
     */
    private String mMessage;
    /**
     * A log priority.
     */
    private DebugLogManager.Priority mPriority;

    /**
     * Constructor.
     *
     * @param kernel   -
     * @param priority -
     * @param source   -
     * @param message  -
     */
    public DebugLogAction(Kernel kernel, DebugLogManager.Priority priority, String source, String message) {
        mLogger = AbstractManager.getInstance(kernel, DebugLogManager.class);
        mSource = source;
        mMessage = message;
        mPriority = priority;
    }

    @Override
    public void execute() {
        mLogger.log(mPriority, mSource, mMessage);
    }
}
