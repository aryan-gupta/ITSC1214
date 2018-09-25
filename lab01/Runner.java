/*
 * This class runs a test on the MyStack and MyList classes
 * @author Aryan Gupta
 * @version 0.2
 */

import java.util.Scanner;
import java.lang.Integer;
import java.io.File;

public final class Runner {
    public static void main(String[] args) throws java.io.FileNotFoundException {

        Scanner file = new Scanner(new File("Lab1InputFile.txt"));

        MyList<InputData>  theList  = new MyList<>();
        MyStack<InputData> theStack = new MyStack<>();

		System.out.println("Reading value triplets...");

        while (file.hasNext()) {
            String name  = file.nextLine();
            String id    = file.nextLine();
            int    hours = Integer.parseInt(file.nextLine());

            theList.add(new InputData(name, id, hours));
		}

		System.out.println("Read lines, Iterator test");
		MyListIterator<InputData> it = theList.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			System.out.print(" ");
		}

		System.out.println("Testing Iterator exception");
		try {
			it.moveForward();
			System.out.println("[Error] Could not catch Exception");
		} catch (java.util.NoSuchElementException e) {
			System.out.println("Caught Exception Successfully");
		}

		System.out.println("\nPushing items into stack...");

		MyListIterator<InputData> iter = theList.iterator();
		while (iter.hasNext()) {
			theStack.push(iter.next());
		}

		System.out.print("Number of items on the stack: ");
		System.out.println(theStack.size());

		System.out.print("The Stack as a String: ");
		System.out.print(theStack);
		System.out.println("\n");

		System.out.println("Popping 3 items... ");
		int num = 3;
		while (num --> 0) {
            theStack.pop();
		}

		System.out.print("Number of items on the stack: ");
		System.out.println(theStack.size());

		System.out.print("The Stack as a String: ");
		System.out.print(theStack);
		System.out.println("\n");

        while (!theStack.empty()) {
            System.out.print("Popped Item: ");
            System.out.println(theStack.pop());
		}

		System.out.print("Popping an empty stack...");

		try {
			theStack.pop();
			System.out.println("[Error] Could not catch Exception");
		} catch (java.util.NoSuchElementException e) {
			System.out.println("Caught Exception Successfully");
		}

		sc.close();
    }
}