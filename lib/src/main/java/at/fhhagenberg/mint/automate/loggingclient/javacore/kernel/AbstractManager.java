package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.DebugLogManager;
import at.fhhagenberg.mint.automate.loggingclient.javacore.name.Id;

/**
 * This class provides default implementation for the {@code Manager} interface.
 * The status of this service and a reference to Kernel is handled as well as
 * dependent services.
 * <p>
 * Developer need only subclass this abstract class and optionally override the
 * following methods:</p>
 * <ul>
 * <li>{@code doStart}</li>
 * <li>{@code doPause}</li>
 * <li>{@code doResume}</li>
 * <li>{@code doStop}</li>
 * </ul>
 */
public abstract class AbstractManager implements Manager {
	/**
	 * Gets the instance of this class from the provided Kernel.
	 * <p>
	 * This kind of magic is from:
	 * http://stackoverflow.com/questions/450807/java-generics-how-do-i-make-the-method-return-type-generic
	 *
	 * @param <T>          the generic type
	 * @param kernel       the Kernel
	 * @param serviceClazz the service clazz
	 * @return single instance of AbstractManager
	 */
	public final static <T extends Manager> T getInstance(Kernel kernel,
														  Class<T> serviceClazz) {
		Id temp = new Id(serviceClazz);
		if (kernel.hasManager(temp)) {
			return serviceClazz.cast(kernel.getManager(temp));
		}
		return null;
	}

	private String mName;
	private Status mStatus;
	private List<Id> mDependencies = new ArrayList<>();

	private Kernel mKernel;

	private String mLoggingSource;
	private DebugLogManager mLogger;

	/**
	 * Defines a {@code Manager} that is initially set to stopped state.
	 */
	public AbstractManager() {
		mStatus = Status.STOPPED;
	}

	/**
	 * Adds the specified service as dependency to this service.
	 *
	 * @param serviceId identifier of the service this service depends on
	 */
	protected final void addDependency(Id serviceId) {
		mDependencies.add(serviceId);
	}

	/**
	 * Removes the specified service as dependency from this service.
	 *
	 * @param serviceId identifier of the service to be removed as dependency
	 */
	protected final void removeDependency(Id serviceId) {
		mDependencies.remove(serviceId);
	}

	/**
	 * Returns the Kernel that administers this service.
	 *
	 * @return -
	 */
	public final Kernel getKernel() {
		if (getStatus() == Status.STOPPED) {
			throw new IllegalStateException();
		}
		return this.mKernel;
	}

	public final DebugLogManager getLogger() {
		if (mLogger == null) {
			mLogger = getInstance(getKernel(), DebugLogManager.class);
		}

		return mLogger;
	}

	/**
	 * Derived classes can override this method in order to do whatever is
	 * necessary to enter <strong>started state</strong>.
	 *
	 * @throws ManagerException -
	 */
	protected void doStart() throws ManagerException {
		// intentionally left empty
	}

	/**
	 * Derived classes can override this method in order to do whatever is
	 * necessary to enter <strong>stopped state</strong>.
	 */
	protected void doStop() {
		// intentionally left empty
	}

	/**
	 * Derived classes can override this method in order to do whatever is
	 * necessary to enter <strong>paused state</strong>.
	 */
	protected void doPause() {
		// intentionally left empty
	}

	/**
	 * Derived classes can override this method in order to do whatever is
	 * necessary to resume <strong>started state</strong>.
	 */
	protected void doResume() {
		// intentionally left empty
	}

	@Override
	public final void start(Kernel kernel) throws ManagerException {
		if (mStatus != Status.STOPPED) {
			throw new IllegalStateException("service " + getName()
					+ " is not in stopped state");
		}
		mKernel = kernel;
		mStatus = Status.STARTED;
		doStart();
	}

	@Override
	public final void stop() {
		if (mStatus != Status.STARTED && mStatus != Status.PAUSED) {
			throw new IllegalStateException("service " + getName()
					+ " is not in started or paused state");
		}
		doStop();
		mStatus = Status.STOPPED;
	}

	@Override
	public final void pause() {
		if (mStatus != Status.STARTED) {
			throw new IllegalStateException("service " + getName()
					+ " is not in started state");
		}
		doPause();
		mStatus = Status.PAUSED;
	}

	@Override
	public final void resume() {
		if (mStatus != Status.PAUSED) {
			throw new IllegalStateException("service " + getName()
					+ " is not in paused state");
		}
		doResume();
		mStatus = Status.STARTED;
	}

	@Override
	public final Status getStatus() {
		return mStatus;
	}

	@Override
	public final int numOfDependencies() {
		return mDependencies.size();
	}

	@Override
	public final Id getDependency(int idx) {
		return mDependencies.get(idx);
	}

	@Override
	public final String getName() {
		if (mName == null) {
			mName = getClass().getName();
		}
		return mName;
	}

	/**
	 * Gets the logging source to identify the log origin.
	 *
	 * @return the logging source
	 */
	public final String getLoggingSource() {
		if (mLoggingSource == null) {
			mLoggingSource = getClass().getSimpleName();
		}

		return mLoggingSource;
	}
}
