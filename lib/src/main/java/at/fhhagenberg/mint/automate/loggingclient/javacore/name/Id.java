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

package at.fhhagenberg.mint.automate.loggingclient.javacore.name;

import java.io.Serializable;

/**
 * Identifier which can be compared and even generated.
 */
public class Id implements Serializable {
    /**
     * The string id.
     */
    private String mId;
    /**
     * A code id which can be the alternative to the string id.
     */
    private int mCode;
    /**
     * Indicator if the id was generated.
     */
    private boolean mIsGenerated;

    /**
     * Create an id from a string name.
     *
     * @param name -
     */
    public Id(String name) {
        mId = name;
        mCode = mId.hashCode();
        mIsGenerated = false;
    }

    /**
     * Make an id from a class name.
     *
     * @param id -
     */
    public Id(Class<?> id) {
        this(id.getName());
    }

    /**
     * Generate an id from a code.
     *
     * @param code -
     */
    @SuppressWarnings("unused")
    Id(int code) {
        mCode = code;
        mIsGenerated = true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mCode;
        result = prime * result + (mIsGenerated ? 1231 : 1237);
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

        Id other = (Id) obj;
        if (mCode != other.mCode) {
            return false;
        }

        if (mIsGenerated != other.mIsGenerated) {
            return false;
        }

        return equalId(other);
    }

    /**
     * Get if this id was generated.
     *
     * @return -
     */
    public boolean isIsGenerated() {
        return mIsGenerated;
    }

    /**
     * Check Id equality.
     *
     * @param other -
     * @return -
     */
    private boolean equalId(Id other) {
        if (mId == null && other != null && other.mId != null) {
            return false;
        }

        return mId.equals(other.mId);
    }

    @Override
    public String toString() {
        if (!mIsGenerated) {
            return mId;
        } else {
            return "0x" + Integer.toHexString(mCode);
        }
    }
}
