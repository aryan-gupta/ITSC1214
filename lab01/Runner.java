
import java.util.Scanner;
import java.lang.Integer;
import java.io.File;

public final class Runner {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        
        Scanner file = new Scanner(new File("Lab1InputFile.txt"));

        MyList<InputData>  theList  = new MyList<>();
        MyStack<InputData> theStack = new MyStack<>();

        while (file.hasNext()) {
            String name  = file.nextLine();
            String id    = file.nextLine();
            int    hours = Integer.parseInt(file.nextLine());

            theList.add(new InputData(name, id, hours));
        }

        for (int i = 0; i < theList.size(); ++i) {
            theStack.push(theList.get(i));
		}
		
		System.out.print("Items on the stack: ");
		System.out.println(theStack.size());

		System.out.print("The Stack as a String: ");
		System.out.println(theStack);

		System.out.print("Popping 3 items: ");
		int num = 3;
		while (num --> 3) {
            theStack.pop();
		}

		System.out.print("Items on the stack: ");
		System.out.println(theStack.size());

		System.out.print("The Stack as a String: ");
		System.out.println(theStack);

        while (!theStack.empty()) {
            System.out.print("Popped Item: ");
            System.out.println(theStack.pop());
		}
		
		try {
			theStack.pop();
			System.out.println("[Error] Could not catch Exception");
		} catch (java.util.NoSuchElementException e) {
			System.out.println("Caught Exception Successfully");
		}
    }
}