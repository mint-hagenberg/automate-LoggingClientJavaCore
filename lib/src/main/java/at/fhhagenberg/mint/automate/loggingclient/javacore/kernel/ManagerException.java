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
 * A {@code ManagerException} is thrown to indicate that the attempt to start a service has failed.
 */
public class ManagerException extends Exception {
	private static final long serialVersionUID = 6195244931574767661L;

	/**
	 * Creates a new {@code ManagerException} with no detail message.
	 */
	public ManagerException() {
		super();
	}

	/**
	 * Creates a new {@code ManagerException} with specified detail message.
	 *
	 * @param message the detail message
	 */
	public ManagerException(String message) {
		super(message);
	}

	/**
	 * Creates a new {@code ManagerException} with specified cause.
	 *
	 * @param cause the cause
	 */
	public ManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new {@code ManagerException} with specified detail message and
	 * cause.
	 *
	 * @param message the detail message
	 * @param cause   the cause
	 */
	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
