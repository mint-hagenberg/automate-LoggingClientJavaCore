package at.fhhagenberg.mint.automate.loggingclient.javacore.util;

import java.util.Arrays;

/**
 * <p> A multiset container (also known as <em>bag</em>) that allows to be iterated
 * and modified at the same time. It is save to add and remove elements while
 * iterating this bag. However this container does not follow the Java
 * Colletiosn Framework API. </p>
 * <p/>
 * <p>
 * Another limitation is that the sequence of inserted elements might not be
 * preserved during different iterations.
 * </p>
 * <p/>
 * <p>
 * Example usage:</p>
 * <p>
 * <pre>
 * Bag&lt;Integer&gt; myBag = new Bag&lt;Integer&gt;();
 * myBag.add(1);
 * myBag.add(2);
 * myBag.add(3);
 *
 * for (myBag.reset(); myBag.hasNext();) {
 * 	System.out.println(&quot;element: &quot; + myBag.next());
 * }
 * </pre>
 *
 * @param <E> the type of elements maintained by this bag
 * @author Roman Divotkey
 */
public class Bag<E> {
	private static final int DEFAULT_CAPACITY = 10;

	private Object[] mElements;
	private int mSize;
	private int mRemaining;
	private int mPos;
	private boolean mElementRemoved = false;

	public Bag(int initialCapacity) {
		mElements = new Object[initialCapacity];
	}

	public Bag() {
		this(DEFAULT_CAPACITY);
	}

	public int size() {
		return mSize;
	}

	public boolean isEmpty() {
		return mSize == 0;
	}

	public boolean add(E e) {
		int length = mElements.length;
		for (int i = mPos; i < length; ++i) {
			if (mElements[i] == null) {
				mElements[i] = e;
				++mSize;
				++mRemaining;
				return true;
			}
		}

		ensureCapacity(length + 1);
		mElements[length] = e;
		++mSize;
		++mRemaining;
		return true;
	}

	public boolean contains(E e) {
		return find(e) != -1;
	}

	public boolean remove(E e) {
		int idx = find(e);
		if (idx != -1) {
			mElements[idx] = null;
			--mSize;
			mElementRemoved = true;
			if (idx >= mPos) {
				--mRemaining;
			}
			return true;
		} else {
			return false;
		}
	}

	public void reset() {
		mPos = 0;
		mRemaining = mSize;

		if (mElementRemoved) {
			optimize();
		}
	}

	private void optimize() {
		int length = mElements.length;
		int cnt = 0;
		int idx = length - 1;

		for (int i = 0; i < length && cnt < mSize; ++i) {
			if (mElements[i] == null) {
				while (mElements[idx] == null) {
					--idx;
				}
				mElements[i] = mElements[idx];
				mElements[idx--] = null;
			}
			++cnt;
		}
	}

	public boolean hasNext() {
		return mRemaining > 0;
	}

	public E get(int idx) {
		int j = 0;
		for (int i = 0; i < mSize; ++i) {
			if (mElements[i] != null && j == idx) {
				break;
			} else {
				j++;
			}
		}

		if (j < mSize && mElements[j] != null) {
			@SuppressWarnings("unchecked")
			E result = (E) mElements[j];
			return result;
		}

		throw new IndexOutOfBoundsException();
	}

	public E next() {
		if (mRemaining <= 0) {
			return null;
		}

		while (mElements[mPos] == null) {
			++mPos;
		}

		--mRemaining;

		@SuppressWarnings("unchecked")
		E result = (E) mElements[mPos++];
		return result;
	}

	private int find(E e) {
		int size = mElements.length;
		for (int i = 0; i < size; ++i) {
			if (e.equals(mElements[i])) {
				return i;
			}
		}

		return -1;
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = mElements.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}

			mElements = Arrays.copyOf(mElements, newCapacity);
		}
	}
}
