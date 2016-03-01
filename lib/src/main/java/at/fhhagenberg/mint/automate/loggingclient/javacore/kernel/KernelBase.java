package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

/**
 * Singleton-like class to manage the Kernel.
 */
public final class KernelBase {
    private static KernelBase sInstance;

    private Kernel mKernel;

    /**
     * Get the Kernel instance if it was instantiated.
     *
     * @return -
     */
    public static Kernel getKernel() {
        if (sInstance == null) {
            throw new NullPointerException(
                    "Kernel not initialized, make sure that initialize is called!");
        }

        return sInstance.mKernel;
    }

    /**
     * Check if the Kernel was initialized.
     *
     * @return -
     */
    public static boolean isInitialized() {
        return sInstance != null;
    }

    /**
     * Initialize the Kernel instance. Should be called when the program starts.
     *
     * @param kernel -
     */
    public static void initialize(Kernel kernel) {
        sInstance = new KernelBase(kernel);
    }

    /**
     * Check if the Kernel is instantiated and running.
     *
     * @return -
     */
    public static boolean isKernelUpRunning() {
        return getKernel().isRunning();
    }

    /**
     * Private constructor to initialize the Kernel singleton.
     *
     * @param kernel -
     */
    private KernelBase(Kernel kernel) {
        mKernel = kernel;
    }
}
