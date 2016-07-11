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
