
package Lab01List;

public class ListNode<T> {
    private ListNode<T> mPrev;
    private T mData;
    private ListNode<T> mNext;

    public ListNode() {
        construct(null, null, null);
    }

    public ListNode(T data) {
        construct(null, data, null);
    }

    public ListNode(ListNode<T> prev, T data) {
        construct(prev, data, null);
    }

    public ListNode(ListNode<T> prev, T data, ListNode<T> next) {
        construct(prev, data, next);
    }

    private void construct(ListNode<T> prev, T data, ListNode<T> next) {
        mPrev = prev;
        mData = data;
        mNext = next;
    }

    public T data() {
        return mData;
    }

    public void data(T data) {
        mData = data;
    }

    public ListNode<T> prev() {
        return mPrev;
    }

    public ListNode<T> next() {
        return mNext;
    }

    public void prev(ListNode<T> node) {
        mPrev = node;
    }

    public void next(ListNode<T> node) {
        mNext = node;
    }


}