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

package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.fhhagenberg.mint.automate.loggingclient.javacore.action.DebugLogAction;
import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;
import at.fhhagenberg.mint.automate.loggingclient.javacore.util.Bag;

/**
 * The Kernel class is the central manager locator used to retrieve manager.
 * <p>
 * The main purpose of this class is to provide a centralized access to
 * manager. Beside this it keeps track about the absolute game time and updates
 * registered updatable objects.
 * <p>
 * <p>
 * An instance of a Kernel can be in two different states:
 * <strong>stopped</strong> and <strong>running</strong>. A newly created Kernel
 * will be initially in <strong>stopped state</strong>. Before any of the added
 * manager can be used the Kernel (and thus its manager) must be started by a
 * call to {@code startup}.
 * </p>
 *
 * @see Manager
 * @see Updatable
 */
@SuppressWarnings("unused")
public class Kernel implements Runnable {
    /**
     * The update interval for updatable manager in ms.
     */
    private static final int THREAD_SLEEP_TIME = 500;

    /**
     * The version of this kernel.
     */
    private static final Version VERSION = new Version(2, 0, 0);

    /**
     * The id-manager map to quickly access managers by id. Registered managers only.
     */
    private Map<Id, Manager> mManagerMap;
    /**
     * A list of registered managers.
     */
    private List<Manager> mManagers;
    /**
     * The list of active kernel listeners.
     */
    private List<KernelListener> mListeners;
    /**
     * List of the updatable items that will be updated in a certain interval.
     */
    private Bag<Updatable> mUpdatables;
    /**
     * Indicates if the kernel is running.
     */
    private boolean mRunning;

    /**
     * A set of disabled managers.
     */
    private Set<Id> mDisabledManagers;

    /**
     * The scheduler that will execute the updatable items in a certain time interval.
     */
    private ScheduledExecutorService mScheduledExecutorService;

    /**
     * Creates a new instance.
     */
    public Kernel() {
        mManagerMap = new HashMap<>();
        mManagers = new ArrayList<>();
        mListeners = new ArrayList<>();
        mUpdatables = new Bag<>();
        mDisabledManagers = new HashSet<>();
    }

    /**
     * Queries if the specified manager exists.
     *
     * @param managerId identifier of the manager that should be queried
     * @return {@code true} if the specified manager exists, {@code false} otherwise
     */
    public boolean hasManager(Id managerId) {
        return mManagerMap.containsKey(managerId);
    }

    /**
     * Retrieves a manager.
     *
     * @param managerId identifier of the manager that should be retrieved
     * @return manager specified by the given identifier
     * @throws RuntimeException if no manager with the specified identifier is available
     */
    public Manager getManager(Id managerId) {
        Manager srv = mManagerMap.get(managerId);
        if (srv == null) {
            throw new RuntimeException("unknown manager: " + managerId);
        }

        return srv;
    }

    /**
     * Adds the specified core listener.
     *
     * @param listener the core listener to be added.
     */
    public void addListener(KernelListener listener) {
        if (mListeners.contains(listener)) {
            throw new RuntimeException("listener already in list: " + listener.getClass().getName());
        }
        mListeners.add(listener);
    }

    /**
     * Removes the specified core listener.
     *
     * @param listener the core listener to be removed
     */
    public void removeListener(KernelListener listener) {
        if (!mListeners.remove(listener)) {
            throw new RuntimeException("listener not registered: " + listener.getClass().getName());
        }
    }

    /**
     * Adds a manager. New manager can only be added if this core is not in
     * <strong>running state</strong>.
     *
     * @param manager the manager to be added
     * @throws IllegalStateException if this core is in running state
     * @throws RuntimeException      if the identifier of the specified manager is ambiguous
     */
    public void addManager(Manager manager) {
        if (mRunning) {
            throw new IllegalStateException("Kernel is running, unable to add manager " + manager.getName());
        }

        Manager oldManager = mManagerMap.put(manager.getId(), manager);
        if (oldManager != null) {
            mManagerMap.put(oldManager.getId(), oldManager);
            throw new RuntimeException("manager with id " + oldManager.getId() + " already added");
        }

        //if (manager.numOfDependencies() > 0) {
        //	for (int i = 0, len = manager.numOfDependencies(); i < len; ++i) {
        //		Id dependencyId = manager.getDependency(i);
        //		if (!hasManager(dependencyId)) {
        //			// TODO: add manager if possible!
        //		}
        //	}
        //}
        mManagers.add(manager);
    }

    /**
     * Removes a manager. Manager can only be removed before if this core is
     * not in <strong>running state</strong>.
     *
     * @param managerId identifier of the manager to be removed.
     * @throws IllegalStateException if this core is running state.
     */
    public void removeManager(Id managerId) {
        if (mRunning) {
            throw new IllegalStateException("Kernel is running, unable to remove manager " + managerId);
        }

        Manager manager = mManagerMap.remove(managerId);
        mManagers.remove(manager);
        // TODO: auto remove dependencies if possible
    }

    /**
     * Starts all manager. After a successful call of this method this core is
     * in <strong>running state</strong>.
     *
     * @throws ManagerException in case at least one manager could not be started
     */
    public void startup() throws ManagerException {
        if (mRunning) {
            throw new IllegalStateException("Kernel is already running");
        }

        mDisabledManagers.clear();
        restoreDisabledManagers();

        for (Manager manager : mManagerMap.values()) {
            if (manager.getStatus() != Manager.Status.STARTED) {
                startManager(manager);
            }
        }
        mRunning = true;
        for (KernelListener listener : mListeners) {
            listener.startupFinished();
        }

        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleWithFixedDelay(this, 0L, THREAD_SLEEP_TIME, TimeUnit.MILLISECONDS);

    }

    /**
     * Get the current time.
     *
     * @return -
     */
    public long getTime() {
        return new Date().getTime();
    }

    /**
     * Start the manager after all the dependencies and update the status.
     *
     * @param manager -
     * @throws ManagerException
     */
    private void startManager(Manager manager) throws ManagerException {
        if (isManagerDisabled(manager.getId())) {
            new DebugLogAction(this, DebugLogManager.Priority.INFO, "Kernel", "Not starting manager as it is globally disabled").execute();
            return;
        }

        int dependencies = manager.numOfDependencies();
        for (int i = 0; i < dependencies; ++i) {
            Manager dependency = mManagerMap.get(manager.getDependency(i));
            if (dependency == null) {
                throw new RuntimeException("unresolved dependency: " + manager.getDependency(i));
            }
            if (dependency.getStatus() != Manager.Status.STARTED) {
                startManager(dependency);
            }
        }

        manager.start(this);
    }

    /**
     * Check if a manager is disabled.
     *
     * @param id -
     * @return -
     */
    protected final boolean isManagerDisabled(Id id) {
        new DebugLogAction(this, DebugLogManager.Priority.INFO, "Kernel", "Looking for " + id + " in " + mDisabledManagers + " found " + mDisabledManagers.contains(id)).execute();
        return mDisabledManagers.contains(id);
    }

    /**
     * Implement to persist disabled managers, depending on the platform implementation.
     */
    protected void storeDisabledManagers() {
        // We don't persist by default
    }

    /**
     * Implement to load persisted disabled managers, depending on the platform implementation.
     */
    protected void restoreDisabledManagers() {
        // We don't persist by default
    }

    /**
     * Disable a manager if not already disabled and stop it.
     *
     * @param id -
     */
    public void disableManager(Id id) {
        boolean added = mDisabledManagers.add(id);
        if (added) {
            Manager manager = getManager(id);
            if (manager != null && manager.getStatus() != Manager.Status.STOPPED) {
                stopManager(manager);
            }
        }
    }

    /**
     * Enable a manager (if it was disabled) and start if necessary.
     *
     * @param id -
     * @throws ManagerException
     */
    public void enableManager(Id id) throws ManagerException {
        boolean removed = mDisabledManagers.remove(id);
        if (removed) {
            Manager manager = getManager(id);
            if (manager != null && manager.getStatus() == Manager.Status.STOPPED) {
                startManager(manager);
            }
        }
    }

    /**
     * Get the disabled manager set.
     *
     * @return -
     */
    protected final Set<Id> getDisabledManagers() {
        return mDisabledManagers;
    }

    private void stopManager(Manager manager) {
        for (Manager srv : mManagerMap.values()) {
            if (srv != manager) {
                if (isDependent(srv, manager.getId()) && srv.getStatus() != Manager.Status.STOPPED) {
                    stopManager(srv);
                }
            }
        }
        manager.stop();
    }

    /**
     * Check if a manager has a dependencies to another one (by id).
     *
     * @param s1        -
     * @param managerId -
     * @return -
     */
    private boolean isDependent(Manager s1, Id managerId) {
        for (int i = 0; i < s1.numOfDependencies(); ++i) {
            if (s1.getDependency(i).equals(managerId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Stops all manager. After a successful call to this method this Kernel is in <strong>stopped state</strong>.
     */
    public void shutdown() {
        new DebugLogAction(this, DebugLogManager.Priority.INFO, "Kernel", "Shutting down kernel").execute();

        mScheduledExecutorService.shutdownNow();

        try {
            mScheduledExecutorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // Ignore
        }

        for (KernelListener listener : mListeners) {
            listener.onPrepareShutdown();
        }

        mRunning = false;
        for (KernelListener listener : mListeners) {
            listener.onShutdown();
        }

        for (Manager manager : mManagerMap.values()) {
            if (manager.getStatus() != Manager.Status.STOPPED) {
                stopManager(manager);
            }
        }

        storeDisabledManagers();
    }

    /**
     * Updates this Kernel. The loop time and all updatable objects will be updated.
     *
     * @see #addUpdatable
     * @see Updatable
     */
    @Override
    public void run() {
        try {
            if (!mRunning) {
                throw new IllegalStateException("core is not running, call startup before update");
            }

            new DebugLogAction(this, DebugLogManager.Priority.VERBOSE, "Kernel", "Pushing update").execute();

            for (mUpdatables.reset(); mUpdatables.hasNext(); ) {
                mUpdatables.next().update();
            }
        } catch (Exception e) {
            // Catch this exception in order to avoid that the scheduler stops on errors
            new DebugLogAction(this, DebugLogManager.Priority.CRITICAL, "Kernel", e.getMessage()).execute();
            for (StackTraceElement ste : e.getStackTrace()) {
                new DebugLogAction(this, DebugLogManager.Priority.CRITICAL, "Kernel", ste.toString()).execute();
            }
        }
    }

    /**
     * Adds the specified updatable object. All updatable objects will be
     * updated whenever the method {@code update} of this Kernel is called.
     * <p>
     * <p>
     * In most cases manager that need to be updated within the update loop cycle
     * implement the interface {@code Updatable}. When such a manager is started
     * it adds itself as updatable object and removes itself when it is stopped.
     * </p>
     *
     * @param updatable the updatable object to be added
     * @see Updatable
     */
    public void addUpdatable(Updatable updatable) {
        mUpdatables.add(updatable);
    }

    /**
     * Removes the specified updatable object.
     *
     * @param updatable the updatable object to be removed
     */
    public void removeUpdateable(Updatable updatable) {
        mUpdatables.remove(updatable);
    }

    /**
     * Retrieves the version of this Kernel.
     *
     * @return version of this Kernel
     */
    public Version getVersion() {
        return VERSION;
    }

    /**
     * Returns the number of manager.
     *
     * @return number of manager.
     */
    public int numManager() {
        return mManagerMap.size();
    }

    /**
     * Retrieves the specified manager.
     *
     * @param idx index of manager to Retrieve
     * @return manager with specified index
     */
    public Manager getManager(int idx) {
        return mManagers.get(idx);
    }

    /**
     * Queries this core if it is in running state.
     *
     * @return {@code true} if this core is in <strong>running state</strong>,
     * {@code false} otherwise
     */
    public boolean isRunning() {
        return mRunning;
    }
}
