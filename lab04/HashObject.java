

/// An object to be hashed into the Hashmap. Will be pupulated with elements
/// from the input file
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

	@Override
	public int hashCode() {
		// There is an old saying in the programming world: Dont be a Dave. Let me tall you his story...
		// Dave, a cool programmer decided that he will rool his own crypto hash function. He creates
		// what he thinks is
		// Ok Im too lazy to type this up...
		// https://security.stackexchange.com/questions/25585/is-my-developers-home-brew-password-security-right-or-wrong-and-why
		// and https://security.stackexchange.com/questions/18197/why-shouldnt-we-roll-our-own

		// A very simple hasher function. ints are 32 bits? char is 8 bits?
		// Divides the string into 4 byte chunks (ints are 4 bytes)
		// and then XOR's all of them together with the int variable

		//  Add padding of 0 until we have multiple of 4
		int padding = 4 - (mString.length() % 4);
		char[] arr = new char[mString.length() + padding]; // this 0 inits our array for us
		mString.getChars(0, mString.length(), arr, 0);

		// an issue I see with this is that `abcdefgh' will hash to the same thing as `efghabcd'
		// collisions arent random, but it will work for a rudimentary hash func. Also, considering
		// our input strings are 3 chars, we should be not getting bad performance with this
		// another issue I see is that readable chars between char(20) to char(126) meaning over half of 
		// the 8bit values arent used and the hash func mapping isnt uniform over 32bit ints
		// An idea would be to multiply anything less than 127 by 2. (0-127) maps to (0-255)
		// and by any chance we do get a char in the unprintable zone (128-255) then divide it
		// by 2 and round towards odd
		
		// for (int i = 0; i < arr.length; ++i) {
		// 	if (arr[i] < 128) {
		// 		arr[i] *= 2;
		// 	} else {
		// 		arr[i] /= 2; // This will always round down
		// 		if (arr[i] % 2 == 0) ++arr[i];
		// 	}
		// }
		
		// This seeds the indivisual chars in the string. because most of our printable chars are with
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