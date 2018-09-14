

final class MyStack<E> extends ListBase<E> {
    public MyStack() {
        super();
    }

    public void push(E e) {
        insertNode(head().next(), e);
    }

    public E pop() {
		if (empty())
			throw new java.util.NoSuchElementException();

        return removeNode(head().next()).data();
    }

    public E peek() {
        return head().next().data();
    }

    public boolean empty() {
        return super.getSize() == 0;
    }

    public int size() {
        return super.getSize();
    }

    public String toString() {
        return "top => " + super.toString();
    }
}