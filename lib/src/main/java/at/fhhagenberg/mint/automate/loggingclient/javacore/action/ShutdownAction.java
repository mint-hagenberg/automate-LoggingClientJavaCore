package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Shut the kernel down.
 */
public class ShutdownAction extends BasicAction {
	/**
	 * Shut a certain kernel down.
	 *
	 * @param kernel -
	 */
	public ShutdownAction(Kernel kernel) {
		super(kernel);
	}

	@Override
	public void execute() {
		getKernel().shutdown();
	}
}
