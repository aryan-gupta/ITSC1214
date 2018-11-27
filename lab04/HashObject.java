


public final class HashObject implements java.lang.Comparable<HashObject> {
	private Integer mInt; // very descriptive I know
	private String mString;

	public HashObject() {
		this(null, null);
	}

	public HashObject(int i, String s) {
		this(Integer.valueOf(i), s);
	}

	public HashObject(Integer i, String s) {
		mInt = i;
		mString = s;
	}

	public Integer theInt() { return mInt; }
	public void theInt(Integer key) { mInt = key; }
	public String theString() { return mString; }
	public void theString(String key) { mString = key; }

	// A very simple hasher function. Divides the string into 4 byte chunks (ints are 4 bytes)
	// and then XOR's all of them together with the int variable
	@Override
	public int hashCode() {
		// ints are 32 bits? char is 8 bits? take chunks of 4 chars and XOR them
		// together. Add padding of 0 until we have multiple of 4
		int padding = 4 - (mString.length() % 4); // how many chars extra do we need to pad
		char[] arr = new char[mString.length() + padding]; // this 0 inits our array for us
		mString.getChars(0, mString.length(), arr, 0);

		int ret_val = mInt;
		for (int i = 0; i < arr.length; i += 4) {
			int xor = ((int)arr[i] << 24) | ((int)arr[i + 1] << 16) | ((int)arr[i + 2] << 8) | ((int)arr[i + 3]);
			ret_val = ret_val ^ xor;
		}

		return ret_val;
	}

	// @Override
	public int hashCodeProper() {
		// There is an old saying in the programming world: Dont be a Dave. Let me tall you his story...
		// Dave, a cool programmer decided that he will rool his own crypto hash function. He creates
		// what he thinks is
		// Ok Im too lazy to type this up...
		// https://security.stackexchange.com/questions/25585/is-my-developers-home-brew-password-security-right-or-wrong-and-why
		// and https://security.stackexchange.com/questions/18197/why-shouldnt-we-roll-our-own
		return mInt.hashCode() ^ mString.hashCode();
	}

	public int compareTo(HashObject o) {
		int c = mInt.compareTo(o.mInt);
		if (c == 0) return mString.compareTo(o.mString);
		return c;
	}

	public String toString() {
		return "(" + mInt + " \"" + mString + "\")";
	}
}