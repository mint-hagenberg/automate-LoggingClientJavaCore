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
