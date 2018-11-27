

/// An object to be hashed into the Hashmap. Will be pupulated with elements
/// from the input file. I will be lacking in commenting this file as most
/// of the public functions are self-explanitory and is not the purpose
/// of this homework assignment
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

	/*
	 * A very rudimentary hash function for an int and a string. The idea is to first go through 
	 * the string and multiply the chars with a prime, this will map the printable chars between
	 * 0 and 255. Then break the string into 4 byte chunks (add padding if needed) and XOR all of
	 * them together with the int variable. Return the absulute value of the int because Java
	 * does not understand what an unsigned variable is.
	 */
	@Override
	public int hashCode() {
		//  Add padding of 0 until we have multiple of 4
		int padding = 4 - (mString.length() % 4);
		char[] arr = new char[mString.length() + padding]; // this 0 inits our array for us
		mString.getChars(0, mString.length(), arr, 0);

		// an issue I see with this is that `abcdefgh' will hash to the same thing as `efghabcd'
		// collisions arent random, but it will work for a rudimentary hash func. Also, considering
		// our input strings are 3 chars, we should be not getting bad performance with this
		
		// This seeds the individual chars in the string. because most of our printable chars are with
		// in 20-126. I wrote a python script to see the actual mapping and they seem random enough
		// for our purposes. (there is a pattern but it isnt an exact pattern)
		for (int i = 0; i < arr.length; ++i) {
			// multiply by prime should farther randomize the bits 
			// but keep a consinstant mapping
			int t = (int)arr[i] * 101;
			arr[i] = (char)(t & 0xFF); // get lowest bits
		}

		int ret_val = mInt;
		for (int i = 0; i < arr.length; i += 4) {
			int xor = ((int)arr[i] << 24) | ((int)arr[i + 1] << 16) | ((int)arr[i + 2] << 8) | ((int)arr[i + 3]);
			ret_val = ret_val ^ xor;
		}

		// This is needed because Java doesnt support unsigned integers
		ret_val = java.lang.Math.abs(ret_val);
		return ret_val;
	}

	// @Override
	// public int hashCode() {
	// 	return mInt.hashCode() ^ mString.hashCode();
	// }

	public int compareTo(HashObject o) {
		int c = mInt.compareTo(o.mInt);
		if (c == 0) return mString.compareTo(o.mString);
		return c;
	}

	public String toString() {
		return "(" + mInt + " \"" + mString + "\")";
	}
}