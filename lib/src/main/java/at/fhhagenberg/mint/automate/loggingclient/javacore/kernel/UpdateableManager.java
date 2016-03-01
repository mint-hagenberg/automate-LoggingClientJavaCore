package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

/**
 * This class provides a default implementation for a service that needs to be
 * updated within a loop.
 * <p>
 * <p>
 * Developer need only subclass this abstract class and optionally override the
 * following methods:
 * </p>
 * <ul>
 * <li>{@code doStart}</li>
 * <li>{@code doPause}</li>
 * <li>{@code doResume}</li>
 * <li>{@code doStop}</li>
 * </ul>
 *
 * @see AbstractManager
 */
public abstract class UpdateableManager extends AbstractManager implements
		Updatable {
	@Override
	protected void doStart() throws ManagerException {
		super.doStart();
		getKernel().addUpdatable(this);
	}

	@Override
	protected void doStop() {
		if (getStatus() != Status.PAUSED) {
			getKernel().removeUpdateable(this);
		}
		super.doStop();
	}

	@Override
	protected void doPause() {
		getKernel().removeUpdateable(this);
		super.doPause();
	}

	@Override
	protected void doResume() {
		getKernel().addUpdatable(this);
		super.doResume();
	}
}
