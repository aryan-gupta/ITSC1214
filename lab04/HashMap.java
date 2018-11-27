
/// An implementation of an HashMap
/// The elements K are hashed and inserted into an array using that
/// hash. \sa HashMap<K>.getIndex(K) for more info, on how the class
/// maps Keys to the index in the array. The basic steps for lookup is
///     - Hash the key
///     - Map the hash to an index (modulus operator)
///     - If the hash collides with another element, create a linked list of collisions at the index
///     - If no collision has occured insert key into the Array at the mapped index
/// Because hashing is "theoretically" O(1), hashtables have O(1) lookup times,
/// insertions, and deletions, as long as there arent too many collisions. 
/// I decided to use prime numbers as the bucket count (Array Table length) to farther
/// intoduce entopy and reduce mapped collisions.  
final public class HashMap<K extends Comparable> {
	// A list of primes to prevent Array index from colliding
	private static final int PRIMES[] = { 11, 23, 47, 101, 199, 401, 797, 1601, 3203, 6397, 12799, 25601, 51199, 102407 }; 
	private int mPrimeIdx; // The current index of the prime above

	private HashNode<K> mTable[]; // The buckets of which to put our data
	private int mSize; // Number of elements in the Table
	private double mLoadFactor; // max load factor acceptable for the table

	public HashMap() {
		mPrimeIdx = 0;
		@SuppressWarnings("unchecked") HashNode<K>[] tmp = new HashNode[PRIMES[mPrimeIdx]];
		mTable = tmp;
		mSize = 0;
		mLoadFactor = 0.75;
	}
	
	// Basic accessors and setters
	public int size() { return mSize; }
	public int buckets() { return mTable.length; }
	public double loadFactor() { return mLoadFactor; }
	public void loadFactor(double lf) { mLoadFactor = lf; }

	// Add a node into our table
	private void add(HashNode<K> newNode) {
		++mSize;
		int idx = getIndex(newNode.key());
		if (mTable[idx] == null) {
			mTable[idx] = newNode;
		} else { // COLLISION
			HashNode<K> node = mTable[idx];
			while (node.next() != null) { node = node.next(); }
			node.next(newNode);
		}
	}

	// Public interface to adding a key value. Will rehash the table is need be
	public void add(K key) {
		double clf = (double)mSize / mTable.length;
		if (clf > mLoadFactor) 
			rehash();
		add(new HashNode<K>(key));
	}

	// Checks if the key is contained in the table 
	public boolean contains(K key) {
		int idx = getIndex(key);
		for (HashNode<K> node = mTable[idx]; node != null; node = node.next()) {
			@SuppressWarnings("unchecked") int cmp = node.key().compareTo(key);
			if (cmp == 0)
				return true;
		}
		return false;
	}

	// Returns the index of where the key should go
	private int getIndex(K key) {
		int hash = key.hashCode();
		return hash % mTable.length;
	}

	private void rehash() {
		++mPrimeIdx;
		mSize = 0;
		HashNode<K>[] oldTable = mTable;
		@SuppressWarnings("unchecked") HashNode<K>[] tmp = new HashNode[PRIMES[mPrimeIdx]];
		mTable = tmp;
		for (int i = 0; i < oldTable.length; ++i) { // go over all the nodes in the old array and move them here
			for (HashNode<K> node = oldTable[i]; node != null;) {
				HashNode<K> nodeNext = node.next();
				node.next(null);
				add(node);
				node = nodeNext;
			}
		}
	}

	// public String toString() {
	// 	java.lang.StringBuilder sb = new java.lang.StringBuilder();
	// 	for (int i = 0; i < mTable.length; ++i) { // go over all the nodes in the old array and move them here
	// 		for (HashNode<K> node = mTable[i]; node != null; node = node.next()) {
	// 			sb.append(node.key());
	// 		}
	// 		sb.append("\n");
	// 	}
	// 	return sb.toString();
	// }
}