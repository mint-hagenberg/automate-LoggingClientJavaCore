package at.fhhagenberg.mint.automate.loggingclient.javacore.action;

import at.fhhagenberg.mint.automate.loggingclient.javacore.kernel.Kernel;

/**
 * Basic abstract action that has a kernel.
 */
public abstract class BasicAction implements Action {
	private Kernel mKernel;

	/**
	 * Initialize with a kernel.
	 *
	 * @param kernel -
	 */
	public BasicAction(Kernel kernel) {
		mKernel = kernel;
	}

	/**
	 * Get the kernel.
	 *
	 * @return -
	 */
	public final Kernel getKernel() {
		return mKernel;
	}
}
