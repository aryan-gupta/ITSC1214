

final public class HashNode<K> {
	private K mKey;
	private HashNode<K> mNext;

	public HashNode(K key) {
		this(key, null);
	}

	public HashNode(K key, HashNode<K> next) {
		mKey = key;
		mNext = next;
	}

	public K key() { return mKey; }
	public void key(K key) { mKey = key; }

	public HashNode<K> next() { return mNext; }
	public void next(HashNode<K> next) { mNext = next; }
}