
/*
 * This class started out trying to mimic the usablility of java.util.List interface;
 * However, due to the hole that appeard in my wall from my head repeatedly contacting it,
 * I decided to leave the List interface intact and define methods that better utilizes a
 * LinkedList benifits.
 */

package Lab01List;

import java.util.Collection;
import java.util.Iterator;

public final class MyList<E> extends ListBase<E> {
	/*
	 * Default C'tor: Constructs an empty list with no data
	 */
	public MyList() {
		super();
	}

	/*
	 * Adds a element to the end of the list
	 * @param e The element to add
	 * @return Always returns true
	 */
	public boolean add(E e) {
		insertNode(tail(), e);
		return true;
	}

	/*
	 * @warning Before I begin to explain what this function does, can we please
	 * discuss how this function is an abomination and an insult to LinkedLists:
	 * This function adds an element at the index \p index, If this was an array,
	 * this function would be cool, but considering that this is a LinkedList, it
	 * totally ignores the porpose of a LinkedList. A LinkedList has the benifit of
	 * having an O(1) insertion as long as we have a pointer to where we want to insert
	 * the data. By using this abonimal function, we drop down to a O(n) time. Because we
	 * first have to iterate through the list until we get to the index. Then we can add
	 * the data. I understand why this is. java.util.Collection has this method and must be
	 * somehow implemented, but that just a terrible design. As such, I will be rewriting
	 * an interator based List, similar to C++ design if I have time. If not:
	 * https://github.com/aryan-gupta/libari/blob/master/include/list.hpp
	 *
	 * Adds the element \p element at the index \p index
	 * @param index The index to add the element to
	 * @param element The item to add
	 */
	public void add(int index, E element) {
		insertNode(idx2node(index), element);
	}

	/*
	 * Adds all the elements from \p c into this
	 * @param c The collection to add from
	 * @return Aways returns true
	 */
	public boolean addAll(Collection<? extends E> c) {
		Iterator<?> it = c.iterator();

		while (it.hasNext()){
			@SuppressWarnings("unchecked")
			E e = (E)it.next();
			add(e);
		}

		return true;
	}

	/*
	 * Adds all the elements from \p c into this at the index \p index
	 * @param index The index to start adding elements
	 * @param c The collection to add from
	 * @return Always returns true
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		Iterator<?> it = c.iterator();

		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			E e = (E)it.next();
			add(index++, e);
		}

		return true;
	}

	/*
	 * Removes all the elemets in this list
	 * postcondition: size() == 0
	 */
	public void clear() {
		reset();
	}

	/*
	 * Checks if \p o is in this
	 * @param o The object to search for
	 * @return If the elements exists in this
	 */
	public boolean contains(Object o) {
		@SuppressWarnings("unchecked")
		E e = (E)o;

		if (find(e) == tail())
			return false;
		return true;
	}

	/*
	 * Checks if all the elements in \p c is in this
	 * @param c The elements to search from
	 * @return If all the elements exits in this
	 */
	public boolean containsAll(Collection<?> c) {
		Iterator<?> it = c.iterator();

		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			E e = (E)it.next();
			if (!contains(e))
				return false;
		}

		return true;
	}

	/*
	 * Returns the i'th element in this, where i == \p index
	 * @param index The index of the element to get
	 * @return The element at \p index
	 */
	public E get(int index) {
		return idx2node(index).data();
	}

	/*
	 * Returns if this is empty
	 * @return If this is empty
	 */
	public boolean isEmpty() {
		return empty();
	}

	/*
	 * Removed the element at index \p index
	 * @param index The index of the element to remove
	 * @return The removed element
	 */
	public E remove(int index) {
		return removeNode(idx2node(index)).data();
	}

	/*
	 * Removes all the occurances of \p o
	 * @param o The object to remove
	 * @return If data was removed from this
	 * @todo this is severly broken and will not work, maybe erase-remove idiom? https://en.wikipedia.org/wiki/Erase–remove_idiom
	 */
	public boolean remove(Object o) {
		@SuppressWarnings("unchecked")
		E e = (E)o;

		boolean removed = false;
		ListNode<E> node = find(e);
		while (node != tail()) {
			remove(node);
			removed = true;
		}
		return removed;
	}

	/*
	 * Removes all the elements from this if it is in \p c
	 * @param c The elements to remove
	 * @return Always returns true
	 */
	public boolean removeAll(Collection<?> c) {
		Iterator<?> it = c.iterator();

		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			E e = (E)it.next();
			remove(e);
		}

		return true;
	}

	/*
	 * Sets the node at index \p index to \p element and returns the old element
	 * @param index The index to modify
	 * @param element The new element to change to
	 * @return The old element
	 */
	public E set(int index, E element) {
		ListNode<E> node = idx2node(index);
		E e = node.data();
		node.data(element);
		return e;
	}

	/*
	 * Returns the size of this
	 * @return The size of this
	 */
	public int size() {
		return super.getSize();
	}

	/*
	 * Returns the String representation of this
	 * @return The string representation of this
	 */
	public String toString() {
		return "head => " + super.toString() + " <= tail";
	}
}