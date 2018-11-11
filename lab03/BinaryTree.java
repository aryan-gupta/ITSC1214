/*
 * This class represents a Binary Search Tree (BST). By definition of a BST
 * the left node of a parent node is less than the parent node, and the right
 * node is greater than or equal to the parent node. The BST can be represented
 * by an array where the left node is 2 * idx + 1 from the parent node, and the
 * right node is at index 2 * idx + 2.
 */


public class BinaryTree<T extends Comparable> {
	private T[] mArray; //< The BST array
	private static final int DEFAULT_SIZE; //< The Default Size (defined in README.md)

	static {
		DEFAULT_SIZE = (int)java.lang.Math.pow(2, 5); // Set DEFAULT_SIZE
	}

	/*
	 * Returns the left child index of the parent (parameter) node
	 * @param idx The parent node index
	 * @return The left child node index
	 */
	private static int left(int idx) {
		return 2 * idx + 1;
	}

	/*
	 * Returns the right child index of the parent (parameter) node
	 * @param idx The parent node index
	 * @return The right child node index
	 */
	private static int right(int idx) {
		return 2 * idx + 2;
	}

	/*
	 * Creates an empty BST with size \p size
	 * @param size The size of the BST (height)
	 */
	public BinaryTree(int size) {
		// The thing with Java Generics is that you can't call the c'tor or any
		// function because you dont know the type. I belive this means that
		// Java uses runtime symantics when it is run. For example, This
		// class will compile to a class called BinaryTree<T>, when we run the
		// executable, Java will plug in Integer (or whatever type the user calls)
		// and then run it. Because of this, we lose the abliity to call member functions
		// unless we know that it exists. This could mean I could theoretically take this
		// file, compile it, then move it to another project and use it. This differs from
		// C++ templates because the template class is initiated at compile time. Meaning
		// when we compile C++ class, The code gets generated then and there. This allows
		// us to call members that the Template parameters could or could not have.

		// Here we cant create a T[] because Java does not know what T is, but Java does
		// know what Comparable is, so we can create an array of Comparable objects, then
		// cast it to a T (T is child class of Comparable). We cant use Object[] because
		// T is a Comparable, but Object isnt. Complicated stuffs here.
		// mArray = new T[size];
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[(int)java.lang.Math.pow(2, size)];
		mArray = tmp;
	}

	/*
	 * Creates BST with an array
	 * @param array The array of which to create a BST of, will be checked if it
	 *        is a BST or not
	 */
	public BinaryTree(T[] array) {
		// converts an array to an BT
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[DEFAULT_SIZE];
		mArray = tmp;

		if (array.length > DEFAULT_SIZE) throw new java.lang.IllegalArgumentException("[E] Largest depth of B(S)T is 5, please construct B(S)T with a smaller array");
		if (!isBSTArray(array)) throw new java.lang.IllegalArgumentException("[E] Array argument is not a BST, please pass in a valid BST");

		int i = 0;
		for(; i < array.length; ++i) {
			mArray[i] = array[i];
		}
	}

	/*
	 * Checks if an array is a BST recursively
	 * @param idx The index to check (will check possible children too)
	 * @param array The array to check
	 */
	private static<T extends Comparable> boolean isBSTArrayBase(int idx, T[] array) {
		if (idx >= array.length) return true; // if we get this far then we are BST leaf
		if (array[idx] == null) return true;

		// make sure left child is less than parent
		if (left(idx) < array.length && array[left(idx)] != null) {
			@SuppressWarnings("unchecked")
			boolean test = array[left(idx)].compareTo(array[idx]) >= 0;
			if (test) return false; // left is bigger or equal
		}

		// make sure right child is greater than or equal to parent
		if (right(idx) < array.length && array[right(idx)] != null){
			@SuppressWarnings("unchecked")
			boolean test = array[right(idx)].compareTo(array[idx]) < 0;
			if (test) return false; // right is smaller
		}

		// check recursively on children
		// if both are true then return true
		// if either is false then return false
		// AND Gate
		return isBSTArrayBase(left(idx), array) && isBSTArrayBase(right(idx), array);
	}

	/*
	 * Checks if an array is a BST recursively
	 * @param array The array to check
	 */
	public static<T extends Comparable> boolean isBSTArray(T[] array) {
		return isBSTArrayBase(0, array);
	}

	/*
	 * Pushes an item into the BST using recursion
	 * @param idx The current index
	 * @param element The element to add
	 */
	private void pushBase(int idx, T element) throws java.lang.Exception {
		if (idx >= mArray.length) throw new java.lang.Exception(); // Yes I know this is bad practice, but I just want it to work for now

		if (mArray[idx] == null) {
			mArray[idx] = element;
			return;
		}

		// continue lookingCRTP
		@SuppressWarnings("unchecked")
		boolean test = mArray[idx].compareTo(element) < 0;
		if (test)
			pushBase(left(idx), element);
		else
			pushBase(right(idx), element); // == is alwasy stored on the right tree
	}

	/*
	 * Pushes an item into the BST
	 * @param element The element to add
	 */
	public void push(T element) {
		try {
			pushBase(0, element);
		} catch(java.lang.Exception e) {
			// balance();
			// pushBase(0, element);
		}
	}

	/*
	 * Recusively searches for an element with worst-case log2(n) time
	 * @param idx The current index
	 * @param element The element to look for
	 */
	private int searchBase(int idx, T element) {
		if (idx >= mArray.length) return -1; // not found
		if (mArray[idx] == null) return -1;

		if (mArray[idx].equals(element)) return idx; // found

		// continue looking
		@SuppressWarnings("unchecked")
		boolean test = mArray[idx].compareTo(element) >= 0;
		if (test)
			return searchBase(left(idx), element);
		else
			return searchBase(right(idx), element);
	}

	/*
	 * Recusively searches for an element with worst-case log2(n) time
	 * @param element The element to look for
	 */
	public int search(T element) {
		return searchBase(0, element);
	}

	/*
	 * Converts this to a String representation
	 * @return The string representation
	 */
	public String toString() {
		java.lang.StringBuilder sb = new java.lang.StringBuilder();

		// inorder(new IterCommand<T>() {

		// 	@Override
		// 	public void exec(T element) {
		// 		sb.append(element);
		// 		sb.append(" ");
		// 	}
		// });

		for(T i : mArray) {
			sb.append(i);
			sb.append(" ");
		}
		return sb.toString();
	}


	//// Everything below here are auxillary functions that ////////
	//// I wrote but aren't part of the assignment so I wont be //////
	//// writing docs for. Most of them are self-explanatory anyways //////
	/*
	public boolean remove(T element) {
		int idx = search(element);
		if (idx != -1) {
			int minIdx = minBase(idx);
			mArray[idx] = mArray[minIdx];
			mArray[minIdx] = null;
			return true;
		}
		return false;
	}

	private int minBase(int idx) {
		if (mArray[left(idx)] != null) {
			return minBase(left(idx));
		}
		return idx;
	}

	public T min() {
		return mArray[minBase(0)];
	}

	private int maxBase(int idx) {
		if (mArray[right(idx)] != null) {
			return minBase(right(idx));
		}
		return idx;
	}

	public T max() {
		return mArray[maxBase(0)];
	}

	// This interface allows us to use lambdas (why must java over complicate this so much)
	public interface IterCommand<T extends Comparable> {
		public void exec(T element);
	}

	private void preorderBase(int idx, IterCommand<T> cmd) {
		if (idx >= mArray.length) return;
		if (mArray[idx] == null) return;

		cmd.exec(mArray[idx]);
		preorderBase(left(idx), cmd);
		preorderBase(right(idx), cmd);
	}

	public void preorder(IterCommand<T> cmd) {
		preorderBase(0, cmd);
	}

	private void inorderBase(int idx, IterCommand<T> cmd) {
		if (idx >= mArray.length) return;
		if (mArray[idx] == null) return;

		inorderBase(left(idx), cmd);
		cmd.exec(mArray[idx]);
		inorderBase(right(idx), cmd);
	}

	public void inorder(IterCommand<T> cmd) {
		inorderBase(0, cmd);
	}

	private void postorderBase(int idx, IterCommand<T> cmd) {
		if (idx >= mArray.length) return;
		if (mArray[idx] == null) return;

		postorderBase(left(idx), cmd);
		postorderBase(right(idx), cmd);
		cmd.exec(mArray[idx]);
	}

	public void postorder(IterCommand<T> cmd) {
		postorderBase(0, cmd);
	}

		/*
	private static<T extends Comparable> void moveRR(T[] orig, T[] newa, int depth, int idx) {
		if (idx >= orig.length) return;
		newa[2 * idx + depth] = orig[idx];
		moveRRR(orig, newa, depth + 2, left(idx));
		moveRRR(orig, newa, depth + 2, right(idx));
	}

	private static<T extends Comparable> void moveRLR(T[] orig, T[] newa, int depth, int idx) {
		if (idx >= orig.length) return;
		newa[idx + depth] = orig[idx];
		moveRLR(orig, newa, depth + 1, left(idx));
		moveRLR(orig, newa, depth + 1, right(idx));
	}

	private static<T extends Comaprable> void moveRLL(T[] orig, T[] newa, int depth, int idx) {
		if (idx >= orig.length) return;
		newa[(idx / 2) + depth] = orig[idx];
		moveRLL(orig, newa, )
	}

	private void rotateRight(int idx) {
		// The root will become the new right
		// The right node will become the root
		// The left node will become the new right node's left
		T tmp = mArray[left(right(idx))];
		// move each left node



		T[] move = new T[mArray.length]; // new array to copy

		// all the childred of the rights children map to 2x + 3, 2x + 5, etc
		moveRRR(mArray, move, 3, left(right(idx)));
		moveRRR(mArray, move, 3, right(right(idx)));

		moveRLR(mArray, move, 1, right(left(idx)));
	}

	private void rotateLeft(int idx) {

	}

	private void rotateRightLeft(int idx) {

	}

	private void rotateLeftRight(int idx) {

	}
	*/
}