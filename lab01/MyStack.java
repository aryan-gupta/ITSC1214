/* 
 * A very simple implementation of a stack using 
 * a linked list as the underlying structure
 */

final class MyStack<E> extends ListBase<E> {
    /* 
     * Creates an empty stack
     */
    public MyStack() {
        super();
    }

    /* 
     * Pushes a item onto the stack
     * @param e The element to add
     */
    public void push(E e) {
        insertNode(head().next(), e);
    }

    /* 
     * Removes the top element off the list and returns it
     * @return The old top element on the stack
     */
    public E pop() {
		if (empty())
			throw new java.util.NoSuchElementException();

        return removeNode(head().next()).data();
    }

    /* 
     * Peeks at the top of the stack, but does not remove it
     * @return The top element on the stack
     */
    public E peek() {
        return head().next().data();
    }

    /* 
     * Returns true if this is empty, false otherwise
     * @return If this is empty or not
     */
    public boolean empty() {
        return super.getSize() == 0;
    }

    /* 
     * Returns the size of this. Returns the elements in this
     * @return The elements in this list
     */
    public int size() {
        return super.getSize();
    }
    
	/*
	 * Returns the String representation of this
	 * @return The string representation of this
	 */
    public String toString() {
        return "top => " + super.toString();
    }
}