



class ListBase<T> /* extends Collection<T> */ {
    private ListNode<T> mHead;
    private ListNode<T> mTail;
    private int mSize;

    protected ListNode<T> head() {
        return mHead;
    }

    protected ListNode<T> tail() {
        return mTail;
    }

    protected int getSize() {
        return mSize;
    }

    protected ListBase() {
        reset();
    }

    private void createSetinal() {
        mHead = new ListNode<T>(null, null, null);
        mTail = new ListNode<T>(mHead, null, null);
        mHead.next(mTail);
    }

    protected void reset() {
        mSize = 0;
        createSetinal();
    }

    protected ListNode<T> idx2node(int index) {
        ListNode<T> currentNode = mHead.next();
        // For those who do not know: https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c
        while (index --> 0) {
            currentNode = currentNode.next();
        }
        return currentNode;
    }

    protected boolean empty() {
        return mHead.next() == mTail;
    }

    protected ListNode<T> find(T data) {
        ListNode<T> node = mHead.next();
        while (node != mTail) {
            if (node.data().equals(data))
                return node;
        }
        return mTail;
    }

    /*
        Inserts a new node before the node with the data
    */
    protected void insertNode(ListNode<T> node, T data) {
        // Create new node with proper prev and next
        ListNode<T> newNode = new ListNode<T>(node.prev(), data, node);
        // Set prev node's next pointer to newNode
        node.prev().next(newNode);
        // Set node's prev pointer to newNode
        node.prev(newNode);
        // Increment size
        ++mSize;
    }

    protected void removeNode(ListNode<T> begin, ListNode<T> end) {
        assert begin != mHead : "[Error] Removal of head node in ListBase<T>.removeNode(ListNode<T>)";
        
        while (begin != end) {
            ListNode<T> toDelete = begin;
            begin = begin.next();
            removeNode(toDelete);
        }
    }

    /*
        Removes node from list
    */
    protected ListNode<T> removeNode(ListNode<T> node) {
        assert node != mHead : "[Error] Removal of head node in ListBase<T>.removeNode(ListNode<T>)";
        assert node != mTail : "[Error] Removal of tail node in ListBase<T>.removeNode(ListNode<T>)";

        // Set prev node's next pointer to next node
        node.prev().next(node.next());
        // Set next node's prev pointer to point to prev
        node.next().prev(node.prev());

        --mSize;

        return node;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        ListNode<T> node = mHead.next();
        
        while (node != mTail) {
            sb.append(node.data().toString());
            sb.append(", ");
            node = node.next();
        }

        return sb.substring(0, sb.length() - 2);
    }
}