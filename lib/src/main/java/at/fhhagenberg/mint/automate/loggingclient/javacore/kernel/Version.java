package at.fhhagenberg.mint.automate.loggingclient.javacore.kernel;

/**
 * Represents a version number of a software package.
 * <p>
 * The mMajor number is increased when there are significant jumps in
 * functionality or the product has been entirely rewritten, the mMinor number is
 * incremented when only mMinor features or significant fixes have been added,
 * and the revision number is incremented when mMinor bugs are fixed.
 */
public class Version {
	private int mMajor;
	private int mMinor;
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
		return new StringBuffer(this.mMajor)
				.append('.')
				.append(this.mMinor)
				.append('.')
				.append(this.mRevision)
				.toString();
	}
}