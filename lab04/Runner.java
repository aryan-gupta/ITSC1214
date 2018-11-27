
import java.util.ArrayList;
import java.util.Scanner;

// In the homework, the input data set is an int and a string. Are these two key value pairs (int is the key, string is the value)?
// or are both of them the key that goes into our custom class and we hash that object?
// > The Lab 4 spec asks you to create an HashObject for each Interger/String pair read from the input file.  These objects
// > should be the "values" hashed into the hash table.  The hash key should be an Integer generated by your hashing function.
// > The hashing function should manipulate data in a HashObject to produce the Integer hash key.

public final class Runner {
	public static HashMap<HashObject> load_hashmap_file(String fname) throws java.io.FileNotFoundException {
		java.util.Scanner file = new Scanner(new java.io.File(fname));
		HashMap<HashObject> ret_val = new HashMap<>();

		while (file.hasNext()) {
			String[] split = file.nextLine().split(" ");
			ret_val.add(new HashObject(Integer.parseInt(split[0]), split[1]));
		}

		file.close();
		return ret_val;
	}

	public static ArrayList<HashObject> load_searchlist_file(String fname) throws java.io.FileNotFoundException {
		java.util.Scanner file = new Scanner(new java.io.File(fname));
		ArrayList<HashObject> ret_val = new ArrayList<>();

		while (file.hasNext()) {
			String[] split = file.nextLine().split(" ");
			ret_val.add(new HashObject(Integer.parseInt(split[0]), split[1]));
		}

		file.close();
		return ret_val;
	}

	public static void main(String[] unused_but_still_need_to_name) throws java.io.FileNotFoundException {
		// System.out.print("custom hash test of: (0, abcd) (should be 1633837924): ");
		// HashObject test = new HashObject(0, "abcd");
		// System.out.println(test.hashCode());

		HashMap<HashObject> map = load_hashmap_file("Lab4InputFile1.txt");
		ArrayList<HashObject> search = load_searchlist_file("Lab4InputFile2.txt");

		for (HashObject item : search) {
			System.out.print("Looking for ");
			System.out.print(item);
			System.out.print(": ");
			if (map.contains(item)) {
				System.out.println("Did     find element");
			} else {
				System.out.println("Did not find element");
			}
		}
	}
}