/*
 * This class runs the other classes and does tests of the BinarySearch algorithm
 * to verify that it is working.
 *
 * @author Aryan Gupta
 * @date 2018/10/9
 */

import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.lang.Integer;
import java.util.StringTokenizer;

class BSRunner {
	public static void main(String[] unused) throws java.io.FileNotFoundException {
		ArrayItem MainItems[] = parseFile("Lab2InputFile1.txt"); // create our array of objects
		ArrayItem SearchItems[] = parseFile("Lab2InputFile2.txt");

		Arrays.sort(MainItems); // sort them

		System.out.println("Sorted Array: "); // print them
		for (ArrayItem ai : MainItems) {
			System.out.println(ai);
		}

		for (ArrayItem ai : SearchItems) { // seach for our elements
			int idx = BinarySearch.bsearch(MainItems, ai);
			if (idx == -1) {
				System.out.println("Item: " + ai.toString() + " not found");
			} else {
				System.out.println("Item: " + ai.toString() + " found at index " + idx);
			}
		}
	}

	/*
	 * Parses a file for Data
	 * @param filename The name of the file to read
	 * @return An array of ArrayItems containing the data of \p filename
	 */
	public static ArrayItem[] parseFile(String filename) throws java.io.FileNotFoundException {
		final int MAX_ITEMS = 30;

		Scanner file = new Scanner(new File(filename));

		// So we cant use a ArrayList or aything like that. I wil declare a array of max size
		// (which is 30), then read in all the data. Then shrink the data set
		ArrayItem temp[] = new ArrayItem[MAX_ITEMS];

		int currentIdx = 0;
		// read data
		while (file.hasNext()) {
			String line  = file.nextLine();
			String color = "", id = "";
			StringTokenizer st = new StringTokenizer(line);

			if (st.hasMoreTokens()) {
				color = st.nextToken();
			}
			// he said we dont need to error check the input

			if (st.hasMoreTokens()) {
				id = st.nextToken();
			}

			temp[currentIdx++] = new ArrayItem(color, Integer.parseInt(id));
		}

		// shrink data to fit
		ArrayItem items[] = new ArrayItem[currentIdx];
		while (currentIdx --> 0) {
			items[currentIdx] = temp[currentIdx];
		}

		return items;
	}
}