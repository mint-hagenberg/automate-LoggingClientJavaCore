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

package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * The interface for managers to be administered by the Kernel.
 *
 * @see Kernel
 */
@SuppressWarnings("unused")
public interface Manager {
    /**
     * Represents the status of a manager.
     */
    enum Status {
        /**
         * Indicates that a manager is not ready to be used and needs to be
         * started.
         */
        STOPPED,

        /**
         * Indicates that a manager has been started and is ready do be used.
         */
        STARTED,

        /**
         * Indicates that a manager has been paused and can be resumed.
         */
        PAUSED
    }

    /**
     * Returns the identifier of this manager.
     *
     * @return identifier of this manager
     */
    Id getId();

    /**
     * Returns the name of this manager.
     *
     * @return human readable name of this manager
     */
    String getName();

    /**
     * Returns the status of this manager.
     *
     * @return the current status of this manager
     */
    Status getStatus();

    /**
     * Starts this manager. After a successful call of this method the status of
     * this manager changes to {@code Status.STARTED}. This method should only
     * be called if this manager is in <strong>started state</strong>.
     *
     * @param core manager locator that administers this manager
     * @throws ManagerException in case this manager could not be started
     * @see #getStatus()
     * @see Status
     */
    void start(Kernel core) throws ManagerException;

    /**
     * Stops this manager. After a successful call of this method the status of
     * this manager changes to {@code Status.STOPPED} This method should only be
     * called if this manager is in <strong>started state</strong>.
     */
    void stop();

    /**
     * Pauses this manager. After a successful call of this method the status of
     * this manager changes to {@code Status.PAUSED}. This method should only be
     * called if this manager is in <strong>started state</strong>.
     */
    void pause();

    /**
     * Resumes this manager. After a successful call of this method the status
     * of this manager changes to {@code Status.STARTED}. This method should
     * only be called if this manager is in <strong>paused state</strong>.
     */
    void resume();

    /**
     * Returns the number of managers this manager depends on.
     *
     * @return number of dependencies
     * @see #getDependency
     */
    int numOfDependencies();

    /**
     * Returns the identifier of a manager this managers depends on.
     * <p>
     * <p>
     * To query all managers this manager depends on, use the following code:
     * </p>
     * <pre>
     * for (int i = 0; i &lt; manager.numOfDependencies(); ++i) {
     * 	CogaenId managerId = manager.getDependency(i);
     * 	doSomeThing(managerId);
     * }
     * </pre>
     *
     * @param idx index of the dependency
     * @return identifier of specified dependency
     */
    Id getDependency(int idx);
}
