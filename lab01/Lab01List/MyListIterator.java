
// import java.util.Iterator;

package Lab01List;

public final class MyListIterator<T> /* implements java.util.Iterator<T> */ {
	private MyList<T> mList;
	private ListNode<T> mCurrent;

	public MyListIterator(MyList<T> list, ListNode<T> start) {
		mList = list;
		mCurrent = start;
	}

	boolean hasNext() {
		return mCurrent.next() != mList
	}
}