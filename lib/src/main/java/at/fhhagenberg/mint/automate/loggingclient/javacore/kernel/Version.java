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
 * Represents a version number of a software package.
 * <p>
 * The mMajor number is increased when there are significant jumps in
 * functionality or the product has been entirely rewritten, the mMinor number is
 * incremented when only mMinor features or significant fixes have been added,
 * and the revision number is incremented when mMinor bugs are fixed.
 */
@SuppressWarnings("unused")
public class Version {
    /**
     * Major part of the version.
     */
    private int mMajor;
    /**
     * Minor part of the version.
     */
    private int mMinor;
    /**
     * Revision part of the version.
     */
    private int mRevision;

    /**
     * Creates a new instance with the specified version information.
     *
     * @param major    the major version number
     * @param minor    the minor version number
     * @param revision the revision number
     */
    public Version(int major, int minor, int revision) {
        mMajor = major;
        mMinor = minor;
        mRevision = revision;
    }

    /**
     * Returns the major version number.
     *
     * @return major version number
     */
    public int getMajor() {
        return mMajor;
    }

    /**
     * Returns the minor version number.
     *
     * @return the minor version number.
     */
    public int getMinor() {
        return mMinor;
    }

    /**
     * Returns the revision number.
     *
     * @return the revision number
     */
    public int getRevision() {
        return mRevision;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mRevision;
        result = prime * result + mMajor;
        result = prime * result + mMinor;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Version other = (Version) obj;
        if (mRevision != other.mRevision) {
            return false;
        }
        if (mMajor != other.mMajor) {
            return false;
        }
        if (mMinor != other.mMinor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder(mMajor).append('.').append(mMinor).append('.').append(mRevision).toString();
    }
}
