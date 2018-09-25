
// import java.util.Iterator;

// My orginal design was to have this iterator store the actual MyList object
// as one of its member variables, however this required either
//    1) marking methods that shouldnt be public public
//    2) creating a package so this class can access MyList but Runner/etc classes cant
// After about 20 min of trying to create a package, I gave up and tried this impl
// I wouldnt dare do option 1 (here marks the death of OOP).

public final class MyListIterator<T> /* implements java.util.Iterator<T> */ {
	final private ListNode<T> mHead;
	private ListNode<T> mCurrent;
	final private ListNode<T> mTail;

	/*
	 * Constructor that sets up the iterator. The head and tail of the original list must
	 * be passed into the iterator to preserve some usablility
	 * @param head The head node from the Container
	 * @param start The node from which to start from
	 * @param tail The tail node in the iteration
	 */
	public MyListIterator(ListNode<T> head, ListNode<T> start, ListNode<T> tail) {
		mHead = head;
		mCurrent = start;
		mTail = tail;
	}

	/*
	 * Returns of the iterator has a next element
	 * @return if the iterator has a next element
	 */
	public boolean hasNext() {
		return mCurrent.next() != mTail;
	}

	/*
	 * Returns of the iterator has a previous element
	 * @return if the iterator has a previous element
	 */
	public boolean hasPrev() {
		return mCurrent.prev() != mHead;
	}

	/*
	 * Increments the iterator forward
	 * @todo would inc()/dec() be a better method name?
	 */
	public void moveForward() {
		if (!hasNext())
			throw new java.util.NoSuchElementException();
		mCurrent = mCurrent.next();
	}

	/*
	 * Increments the iterator backwards
	 */
	public void moveBackward() {
		if (!hasPrev())
			throw new java.util.NoSuchElementException();
		mCurrent = mCurrent.prev();
	}

	/*
	 * Increments the iterator forward and returns the element
	 * @return the next element in the iteration
	 */
	public T next() {
		moveForward();
		return mCurrent.data();
	}

	/*
	 * Decrements the iterator backwards and returns the element
	 * @return the previous element in the iteration
	 */
	public T prev() {
		moveBackward();
		return mCurrent.data();
	}

	/*
	 * Returns the current element
	 * @return the current element
	 */
	public T get() {
		return mCurrent.data();
	}

	/// This link explains what Im doing here (only allows ListBase and other child classes to call this meathod):
	/// https://stackoverflow.com/questions/182278/is-there-a-way-to-simulate-the-c-friend-concept-in-java
	/*
	 * Returns the node that the iterator points to
	 */
	public ListNode<T> getNode(ListBase.FriendClass fc) {
		return mCurrent;
	}
}