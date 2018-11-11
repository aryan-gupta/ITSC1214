import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

class Runner {
	/*
	 * Loads a Binary Search Tree from a file. Assumptions made:
	 *  - First element is the node
	 *  - Second element is the left element of the First element
	 *  - Third element is the right element of the First element
	 *  - 4th element is the left element of the left element of the root node
	 *  - ....
	 * See README.md for more details
	 */
	public static BinaryTree<Integer> load_bst_file(String str) throws FileNotFoundException {
		java.util.Scanner file = new Scanner(new java.io.File(str));
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		while (file.hasNext()) {
			String line = file.nextLine();
			String[] split = line.split(" ");
			// ret.push(Integer.parseInt(split[1]));
			tmp.add(Integer.parseInt(split[1]));
		}

		// convert our ArrayList into a raw array. To do that we need to pass in an empty array of size least 1
		// howeer I have heard that Java will use the array we pass in if the ArrayList passed in is large enough
		// for the data, this will save time on multiple memory allocations.
		Integer array[] = tmp.toArray(new Integer[(int)java.lang.Math.pow(2, 5)]);

		BinaryTree<Integer> ret = new BinaryTree<Integer>(array);

		file.close();
		return ret;
	}

	/*
	 * Loads the Search file
	 */
	public static Integer[] load_search_file(String str) throws FileNotFoundException {
		java.util.Scanner file = new Scanner(new java.io.File(str));
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		while (file.hasNext()) {
			String line = file.nextLine();
			tmp.add(Integer.parseInt(line));
		}

		Integer[] ret = tmp.toArray(new Integer[tmp.size()]);

		file.close();
		return ret;
	}

	/*
	 * Returns the number of digits in a number recursively
	 * See Runner.get_digits(int)
	 */
	private static int get_digits_recurse(int num, int depth) {
		if (num == 0) return depth;
		return get_digits_recurse((int)(num / 10), depth + 1);
	}

	/*
	 * Returns the number of digits in a number recursively
	 *  - 1000 => 4
	 *  - 1    => 1
	 *  - 123  => 3
	 */
	public static int get_digits(int num) {
		return get_digits_recurse(num, 1);
	}

	public static void main(String[] args) throws FileNotFoundException {
		BinaryTree<Integer> tree = load_bst_file("Lab3InputFile1.txt");
		Integer[] search = load_search_file("Lab3InputFile2.txt");

		System.out.println("All indices are 0 based, depth 0 is the root node");

		for (Integer i : search) {
			System.out.print("Looking for element");
			for (int j = 4 - get_digits(i); j >= 0; --j) System.out.print(" ");
			System.out.print(i);
			System.out.print(":");
			int index = tree.search(i);
			if (index == -1) {
				System.out.println(" not found");
			} else {
				System.out.print("     found at index");
				for (int j = 3 - get_digits(index); j >= 0; --j) System.out.print(" ");
				System.out.print(index);
				System.out.print(" at depth ");
				System.out.println((int)(Math.log(index)/Math.log(2)));
			}
		}
	}
}