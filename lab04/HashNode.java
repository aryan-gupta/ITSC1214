
/// A node in the hashtable. THe reason this exists is because of 2 reasons. 
///    - A way to manage collisions. The collision is linked with a fwd list using mNext
///    - A future edit where we store the actual hash here so we dont have to rehash the key evertime
///           we exceed the load factor
/// This class holds the key and a pointer to the next element that collided with our hash
final public class HashNode<K> {
	private K mKey;
	private HashNode<K> mNext;

	private int mHash;

	public HashNode(K key) {
		this(key, null);
	}

	public HashNode(K key, HashNode<K> next) {
		mKey = key;
		mNext = next;
		mHash = key.hashCode();
	}

	/// Accessors and setters
	public int hash() { return mHash; }
	
	public K key() { return mKey; }
	public void key(K key) { mKey = key; }

	public HashNode<K> next() { return mNext; }
	public void next(HashNode<K> next) { mNext = next; }
}