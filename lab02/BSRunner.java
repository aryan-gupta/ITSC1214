
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.lang.Integer;
import java.util.StringTokenizer;

class BSRunner {
	public static void main(String[] unused) throws java.io.FileNotFoundException {
		ArrayItem MainItems[] = parseFile("Lab2InputFile1.txt");
		ArrayItem SearchItems[] = parseFile("Lab2InputFile2.txt");

		Arrays.sort(MainItems);

		System.out.println("Sorted Array: ");
		for (ArrayItem ai : MainItems) {
			System.out.println(ai);
		}

		for (ArrayItem ai : SearchItems) {
			int idx = BinarySearch.bsearch(MainItems, ai);
			if (idx == -1) {
				System.out.println("Item: " + ai.toString() + " not found");
			} else {
				System.out.println("Item: " + ai.toString() + " found at index " + idx);
			}
		}
	}

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

	public static void testAll() {
		lookFor(0, 15); // empty set and not found test
		for (int i = 0; i < 10; ++i) { // edge and mid test
			lookFor(10, i);
		}
	}

	public static void lookFor(int size, int dat) {
		System.out.println();
		System.out.println();
		System.out.println(dat);

		Integer data[] = new Integer[size];
		for (int i = 0; i < data.length; ++i) {
			data[i] = i; //(int)(Math.random() * 100);
		}

		for (Integer a : data) {
			System.out.print(a);
			System.out.print(" ");
		}
		System.out.println();

		Arrays.sort(data);

		int idx = BinarySearch.bsearch(data, dat);

		for (Integer a : data) {
			System.out.print(a);
			System.out.print(" ");
		}
		System.out.println();

		if (idx == -1)
			System.out.println("[I] Not Found");
		else
			System.out.println(data[idx]);
	}
}