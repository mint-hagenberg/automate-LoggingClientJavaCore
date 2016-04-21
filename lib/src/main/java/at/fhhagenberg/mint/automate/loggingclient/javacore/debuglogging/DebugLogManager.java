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

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LevelFilter;
import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.AbstractManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.ManagerException;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * A simple manager that can process debug logging information. Only used internally to usually output textual log messages to the console or similar.
 */
@SuppressWarnings("unused")
public class DebugLogManager extends AbstractManager {
    /**
     * Manager type id.
     */
    public static final Id ID = new Id(DebugLogManager.class);

    /**
     * Possible log priorities.
     */
    public enum Priority {
        VERBOSE, DEBUG, INFO, WARNING, ERROR, CRITICAL, ASSERT;
    }

    /**
     * A verbose-minimum log level filter.
     */
    public static LogFilter VERBOSE_LEVEL_FILTER = new LevelFilter(Priority.VERBOSE);
    /**
     * A debug-minimum log level filter.
     */
    public static LogFilter DEBUG_LEVEL_FILTER = new LevelFilter(Priority.DEBUG);
    /**
     * A info-minimum log level filter.
     */
    public static LogFilter INFO_LEVEL_FILTER = new LevelFilter(Priority.INFO);
    /**
     * A warning-minimum log level filter.
     */
    public static LogFilter WARNING_LEVEL_FILTER = new LevelFilter(Priority.WARNING);
    /**
     * An error-minimum log level filter.
     */
    public static LogFilter ERROR_LEVEL_FILTER = new LevelFilter(Priority.ERROR);
    /**
     * A critical-minimum log level filter.
     */
    public static LogFilter CRITICAL_LEVEL_FILTER = new LevelFilter(Priority.CRITICAL);
    /**
     * A assert-minimum log level filter.
     */
    public static LogFilter ASSERT_LEVEL_FILTER = new LevelFilter(Priority.ASSERT);

    private List<LogListener> mListeners = new ArrayList<>();

    /**
     * Default constructor.
     */
    public DebugLogManager() {
    }

    /**
     * Initialize with listeners.
     *
     * @param listener -
     */
    public DebugLogManager(LogListener... listener) {
        if (listener != null) {
            for (int i = 0; i < listener.length; i++) {
                addListener(listener[i]);
            }
        }
    }

    /**
     * Add a log listener.
     *
     * @param listener -
     */
    public final void addListener(LogListener listener) {
        mListeners.add(listener);

        if (getStatus() == Status.STARTED) {
            listener.doStart(this);
        }
    }

    /**
     * Remove a log listener.
     *
     * @param listener -
     */
    public final void removeListener(LogListener listener) {
        mListeners.remove(listener);
    }

    /**
     * FIlter a log using the internal log filters.
     *
     * @param priority -
     * @param source   -
     * @param message  -
     */
    private void filterLog(Priority priority, String source, String message) {
        for (LogListener listener : mListeners) {
            boolean log = true;
            for (LogFilter filter : listener.getFilters()) {
                if (!filter.accept(priority, source, message)) {
                    log = false;
                    break;
                }
            }

            if (log) {
                listener.doLogMessage(priority, source, message);
            }
        }
    }

    /**
     * Filter a log using the internal log filters.
     *
     * @param priority -
     * @param source   -
     * @param message  -
     */
    private void filterLog(Priority priority, String source, Object message) {
        for (LogListener listener : mListeners) {
            boolean log = true;
            for (LogFilter filter : listener.getFilters()) {
                if (!filter.accept(priority, source, message)) {
                    log = false;
                    break;
                }
            }

            if (log) {
                listener.doLogMessage(priority, source, message);
            }
        }
    }

    /**
     * Logs a message with specified priority.
     *
     * @param priority priority of this logging entry.
     * @param source   logging source of this message.
     * @param message  message text to be logged.
     */
    public final void log(Priority priority, String source, String message) {
        filterLog(priority, source, message);
    }

    /**
     * Logs a message with specified priority.
     *
     * @param priority priority of this logging entry.
     * @param source   logging source of this message.
     * @param message  message text to be logged.
     */
    public final void log(Priority priority, String source, Object message) {
        filterLog(priority, source, message);
    }

    /**
     * Logs a message with priority 'debug'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logDebug(String source, String message) {
        filterLog(Priority.DEBUG, source, message);
    }

    /**
     * Logs a message with priority 'info'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logInfo(String source, String message) {
        filterLog(Priority.INFO, source, message);
    }

    /**
     * Logs a message with priority 'verbose'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logVerbose(String source, String message) {
        filterLog(Priority.VERBOSE, source, message);
    }

    /**
     * Logs a message with priority 'warning'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logWarning(String source, String message) {
        filterLog(Priority.WARNING, source, message);
    }

    /**
     * Logs a message with priority 'error'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logError(String source, String message) {
        filterLog(Priority.ERROR, source, message);
    }

    /**
     * Logs a message with priority 'critical'.
     *
     * @param source    logging source of this message.
     * @param throwable message text to be logged.
     */
    public final void logCritical(String source, Throwable throwable) {
        for (LogListener listener : mListeners) {
            listener.doLogMessage(Priority.CRITICAL, source, throwable);
        }
    }

    /**
     * Logs a message with priority 'assert'.
     *
     * @param source  logging source of this message.
     * @param message message text to be logged.
     */
    public final void logAssert(String source, String message) {
        filterLog(Priority.ASSERT, source, message);
    }

    @Override
    protected void doStart() throws ManagerException {
        super.doStart();

        for (LogListener listener : mListeners) {
            listener.doStart(this);
        }
    }

    @Override
    protected void doStop() {
        for (LogListener listener : mListeners) {
            listener.doStop(this);
        }
        super.doStop();
    }

    @Override
    public Id getId() {
        return ID;
    }
}
