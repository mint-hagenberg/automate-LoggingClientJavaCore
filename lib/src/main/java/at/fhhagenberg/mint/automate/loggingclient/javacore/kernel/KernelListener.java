package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

/**
 * The interface for kernel listener.
 */
public interface KernelListener {
	/**
	 * This method is called after all services have been started.
	 */
	void startupFinished();

	/**
	 * Called before the kernel is shut down.
	 */
	void onPrepareShutdown();

	/**
	 * Called if the core is about to shut down.
	 */
	void onShutdown();
}
