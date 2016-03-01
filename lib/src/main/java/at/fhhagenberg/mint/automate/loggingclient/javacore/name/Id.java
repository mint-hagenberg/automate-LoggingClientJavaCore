package at.fhhagenberg.mint.automate.loggingclient.javacore.name;

/**
 * Identifier which can be compared and even generated.
 */
public class Id {
    private String mId;
    private int mCode;
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
        mId = id.getName();
        mCode = id.hashCode();
        mIsGenerated = false;
    }

    /**
     * Generate an id from a code.
     *
     * @param code -
     */
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

        assert (equalId(other));

        return true;
    }

    /**
     * Get if this id was generated.
     *
     * @return -
     */
    public boolean isIsGenerated() {
        return mIsGenerated;
    }

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
