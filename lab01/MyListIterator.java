
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

	public MyListIterator(ListNode<T> head, ListNode<T> start, ListNode<T> tail) {
		mHead = head;
		mCurrent = start;
		mTail = tail;
	}

	public boolean hasNext() {
		return mCurrent.next() != mTail;
	}

	public boolean hasPrev() {
		return mCurrent.prev() != mHead;
	}

	public void moveForward() {
		if (!hasNext())
			throw new java.util.NoSuchElementException();
		mCurrent = mCurrent.next();
	}

	public void moveBackward() {
		if (!hasPrev())
			throw new java.util.NoSuchElementException();
		mCurrent = mCurrent.prev();
	}

	public T next() {
		moveForward();
		return mCurrent.data();
	}

	public T prev() {
		moveBackward();
		return mCurrent.data();
	}

	public T get() {
		return mCurrent.data();
	}

	/// This link explains what Im doing here (only allows ListBase and other child classes to call this meathod):
	/// https://stackoverflow.com/questions/182278/is-there-a-way-to-simulate-the-c-friend-concept-in-java
	public ListNode<T> getNode(ListBase.FriendClass fc) {
		return mCurrent;
	}
}