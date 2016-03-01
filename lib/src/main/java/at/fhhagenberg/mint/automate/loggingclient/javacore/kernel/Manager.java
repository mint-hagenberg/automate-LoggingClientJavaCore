package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * The interface for managers to be administered by the Kernel.
 *
 * @see Kernel
 */
public interface Manager {
	/**
	 * Represents the status of a manager.
	 */
	enum Status {
		/**
		 * Indicates that a service is not ready to be used and needs to be
		 * started.
		 */
		STOPPED,

		/**
		 * Indicates that a service has been started and is ready do be used.
		 */
		STARTED,

		/**
		 * Indicates that a service has been paused and can be resumed.
		 */
		PAUSED
	}

	/**
	 * Returns the identifier of this service.
	 *
	 * @return identifier of this service
	 */
	Id getId();

	/**
	 * Returns the name of this service.
	 *
	 * @return human readable name of this service
	 */
	String getName();

	/**
	 * Returns the status of this service.
	 *
	 * @return the current status of this service
	 */
	Status getStatus();

	/**
	 * Starts this service. After a successful call of this method the status of
	 * this service changes to {@code Status.STARTED}. This method should only
	 * be called if this service is in <strong>started state</strong>.
	 *
	 * @param core service locator that administers this service
	 * @throws ManagerException in case this service could not be started
	 * @see #getStatus()
	 * @see Status
	 */
	void start(Kernel core) throws ManagerException;

	/**
	 * Stops this service. After a successful call of this method the status of
	 * this service changes to {@code Status.STOPPED} This method should only be
	 * called if this service is in <strong>started state</strong>.
	 */
	void stop();

	/**
	 * Pauses this service. After a successful call of this method the status of
	 * this service changes to {@code Status.PAUSED}. This method should only be
	 * called if this service is in <strong>started state</strong>.
	 */
	void pause();

	/**
	 * Resumes this service. After a successful call of this method the status
	 * of this service changes to {@code Status.STARTED}. This method should
	 * only be called if this service is in <strong>paused state</strong>.
	 */
	void resume();

	/**
	 * Returns the number of services this service depends on.
	 *
	 * @return number of dependencies
	 * @see #getDependency
	 */
	int numOfDependencies();

	/**
	 * Returns the identifier of a service this services depends on.
	 * <p>
	 * <p>
	 * To query all services this service depends on, use the following code:
	 * </p>
	 * <pre>
	 * for (int i = 0; i &lt; service.numOfDependencies(); ++i) {
	 * 	CogaenId serviceId = service.getDependency(i);
	 * 	doSomeThing(serviceId);
	 * }
	 * </pre>
	 *
	 * @param idx index of the dependency
	 * @return identifier of specified dependency
	 */
	Id getDependency(int idx);
}
