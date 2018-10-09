

class BinarySearch {
	public static <T extends Comparable<T>> int bsearch(T[] array, T object) {
		return bsearch(array, 0, array.length, object);
	}


	public static <T extends Comparable<T>> int bsearch(T[] array, int begin, int end, T object) {
		assert end < begin : "[E] Size of search area is less than 0";

		if (end - begin < 2) return -1;

		int mid = (end + begin) / 2;
		int cmp = array[mid].compareTo(object);

		if (cmp > 0) {
			return bsearch(array, begin, mid, object);
		} else if (cmp < 0) {
			return bsearch(array, mid, end, object);
		} else { // (cmp == 0)
			return mid;
		}
	}
}