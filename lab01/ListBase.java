/*
 * A list base. Contains common methods and algorithms to modify a
 * linked-list type structure. Ruberic only described a fwd list,
 * however my lack of reading skills made me create a doubly-linked
 * list. Most functions declared protected because this class is not
 * supposed to be instantiated on its own. This class is by no means
 * a full LinkedList impl, I created most of the methods as they were
 * needed.
 * @note One of the issues I see is the size, we can either keep the
 *       size cached in mSize or we can calculate it on the fly, this
 *       will help with remove(begin, end), as we dont have to calculate
 *       the distance between begin and end.
 * @author Aryan Gupta
 * @version 0.7
 */

public class ListBase<T> /* extends Collection<T> */ {
    /*
     * So this class is weird. The purpose of this class it to make certain functions
     * only callable by some classes. For example \sa MyListIterator has a function
     * \sa getNode(FriendClass). The parameter of this function is this class. But because
     * the constructor of the class is private only ListBase and chile classes can call
     * the method (only this class and child classes can instantiate the class). There
     * is more info on where I go this from if you see \sa getNode(FriendClass)
     */
    public static final class FriendClass {
        private FriendClass() {  }
    }

    /// Single instatiation to revent duplicate class instances
    protected static final FriendClass FRIEND_CLASS = new FriendClass();

    private ListNode<T> mHead; //< Head node
    private ListNode<T> mTail; //< Tail node
    private int mSize; //< Size of the list. Prevents traversal-count when inquiring about size

    /*
     * Returns the head node
     * @return The head node
     */
    protected ListNode<T> head() {
        return mHead;
    }

    /*
     * Returns the tail node
     * @return The tail node
     */
    protected ListNode<T> tail() {
        return mTail;
    }

    /*
     * Returns the size of this. Returns (distance between mHead and mTail) - 1
     * @note Couldn't rename this function size() cause Java inheritance rules
     * @return the size of this
     */
    protected int getSize() {
        return mSize;
    }

    /*
     * Default C'tor: Creates a empty list with setinal nodes
     */
    protected ListBase() {
        reset();
    }

    /*
     * Creates setinal nodes. There are ways to remove these nodes but
     * the questions always goes back to performance over memory. Without
     * setinal nodes, it will have less branching and better performance.
     * Decided to use setinal nodes cause it makes my life easier and the
     * less time I get to see Java, the better.
     */
    private void createSetinal() {
        mHead = new ListNode<T>(null, null, null);
        mTail = new ListNode<T>(mHead, null, null);
        mHead.next(mTail);
    }

    /*
     * Clears the list and recreates the setinal nodes. Thank god for gc
     * for making this so easy (compared to c++)
     */
    protected void reset() {
        mSize = 0;
        createSetinal();
    }

    /*
     * This abominal function is like taking a Cray supercomputer and
     * trying to run Minesweeper on it. Thus, I will refuse to comment
     * what this does to prevent future self from using it.
     * \sa MyList<E>.add(int,E) for more info
     * @deprecated
	 */
	@Deprecated
    protected ListNode<T> idx2node(int index) {
        ListNode<T> currentNode = mHead.next();

        while (index --> 0) {
            currentNode = currentNode.next();
        }

        return currentNode;
    }

    /*
     * Returns if this is empty and has no elements in it
     * @return If this has no elements in it
     */
    protected boolean empty() {
        return mHead.next() == mTail;
    }

    /*
     * Finds element T in this
     * @param data The element to find
     * @return The node with that data
     */
    protected ListNode<T> find(T data) {
        ListNode<T> node = mHead.next();

        while (node != mTail) {
            if (node.data().equals(data))
                return node; // break; // If java releases somthing equvilant to c++ constexpr
        }

        return node;
    }

    /*
     * Inserts a new node before the node with the data
     * @param node The node to insert before
     * @param data The data to insert
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

    /*
     * Removed a series of nodes from this list: [begin, end)
     * @warning begin node MUST be before end node or it is UB
     * @param begin The first node to remove
     * @param end the one-past-end node to remove
     * @todo Add splicing support to this function
     */
    protected void removeNode(ListNode<T> begin, ListNode<T> end) {
        assert begin != mHead : "[Error] Removal of head node in ListBase<T>.removeNode(ListNode<T>,ListNode<T>)";

        // Set begin's previous nodes next pointer to end
        begin.prev().next(end);
        // Set end's prev pointer to begin's prev
        end.prev(begin.prev());

        // Go though the nodes and count how many we removed
        while (begin != end) {
            --mSize;
            begin = begin.next();
        }
    }

    /*
     * Removes single node from list and returns that node
     * @param node The node to remove
     * @return The removed node
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

    /*
     * String representation of this object
     * @return The String representation of this object
     */
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