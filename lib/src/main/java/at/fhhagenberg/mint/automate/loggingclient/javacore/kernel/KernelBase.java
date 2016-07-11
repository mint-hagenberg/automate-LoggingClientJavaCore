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

/**
 * Singleton-like class to manage the Kernel.
 */
@SuppressWarnings("unused")
public final class KernelBase {
    /**
     * Singleton instance.
     */
    private static KernelBase sInstance;

    /**
     * The initialized kernel.
     */
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
        return isInitialized() && getKernel().isRunning();
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
