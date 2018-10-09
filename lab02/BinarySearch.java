/*
 * This class does a binary search for an object. The array must be sorted and
 * must have a compareTo meathod (enforced by `T extends Comparable<T>`). Binary
 * Seach may be called on a range or on the whole array. Facilities for whole
 * Array lookup is given.
 *
 * @author Aryan Gupta
 * @date 2018/10/9
 */

class BinarySearch {
	/*
	 * Runs a binary search on an entire array. The array must be sorted
	 * @warning Unsorted array will lead to undefined behavior
	 *
	 * @param array The array to run binary search on.
	 * @param object The object to look for
	 * @return The index of the object, -1 if not found (object == array[@return])
	 */
	public static <T extends Comparable<T>> int bsearch(T[] array, T object) {
		return bsearch(array, 0, array.length, object); // call search on entire array
	}

	/*
	 * Runs a binary search on an section of an array. The array section must be sorted
	 * in non-decending order.
	 * @warning Unsorted array will lead to undefined behavior
	 *
	 * @param array The array to run binary search on.
	 * @param begin The starting index from where to start the search
	 * @param end The one-past-end index to stop searching for
	 * @param object The object to look for
	 * @return The index of the object, -1 if not found (object == array[@return])
	 */
	public static <T extends Comparable<T>> int bsearch(T[] array, int begin, int end, T object) {
		assert end < begin : "[E] Size of search area is less than 0";

		if (end - begin < 2) return -1;

		int mid = (end + begin) / 2;
		int cmp = array[mid].compareTo(object);

		if (cmp > 0) { // object is smaller, must be before mid
			return bsearch(array, begin, mid, object);
		} else if (cmp < 0) { // object is bigger, must be after mid
			return bsearch(array, mid, end, object);
		} else { // (cmp == 0)
			return mid;
		}
	}
}