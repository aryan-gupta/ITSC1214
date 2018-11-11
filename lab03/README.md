This exercise is intended to demonstrate using an array to build a binary tree to be used as a binary search tree.   You will build your binary tree using the computational strategy described in our text and class lectures.  The maximum size of the array should be defined to accommodate a binary tree of height 5.

You will write a Java program that has at least one function:  main.  main will be the entry point/driver of your program.  You may isolate the functionality of your solution by creating additional functions if you wish.Two data files will be provided.  Lab3InputFile1.txt will contain node records having two fields:  the value of the parent node and the value of a node itself.  The values will be positive integers >= 0.

You should add the nodes to the tree in inorder sequence using the rules of a binary tree.  (The first record is the root.  The second record is the left child of the root.  The third record is the right child of the root.  The fourth record is left child of the left child of the root.  Etc.)  Doing so will produce a full tree.

The record for the root will have a parent value of -1.  Suggestion:  Use a negative number (-1) as the default value of an array element to indicate a position in the array for a node that is not read from the file.You may recall that the computational strategy reserves an element in the array for every possible node of a tree for any given height and order.  When loading the tree you may use Java class function(s) (Arrays.binarySearch()?) to find the index of the parent to facilitate your calculation of the indexes of the children.The second file, Lab3InputFile2.txt, contains a list of search integers.

After you have read the tree nodes from Lab3InputFile1 and loaded them into your array, you should write your own function to use the search functionality of the binary tree to determine if the search integers are in the tree or not.You should print a clearly labeled, formatted report of your results containing each search integer and whether it is found or not.  If it is found, include the index of the binary tree array and the level of the tree.  Your program should work for input files of any size representing a tree no greater than height 5.  Your program should not be dependent on the sequence of the search list.  When executing your program In NetBeans, the working directory is the directory of your project.

To facilitate us evaluating your work, add the two data files to your project directory.  Do not change the file names.  All internal references to the files in your program should be the file name only.  Do not include the full path.Submit your work as a .zip file as detailed in the Lab Submission Guidelines document.


VER 2:

This exercise is intended to demonstrate using an array to build a binary tree to be used as a binary search tree.   You will build your binary tree using the computational strategy described in our text and class lectures.  The maximum size of the array should be defined to accommodate a binary tree of height 5.

You will write a Java program that has at least one function:  main.  main will be the entry point/driver of your program.  You may isolate the functionality of your solution by creating additional functions if you wish.

Two data files will be provided.  Lab3InputFile1.txt will contain node records having two fields:  the value of the parent node and the value of a node itself.  The values will be positive integers >= 0.  You should add the nodes to the tree in inorder sequence using the rules of a binary tree.  (The first record is the root.  The second record is the left child of the root.  The third record is the right child of the root.  The fourth record is left child of the left child of the root.  Etc.)  Doing so will produce a full tree.  The record for the root will have a parent value of -1.  Suggestion:  Use a negative number (-1) as the default value of an array element to indicate a position in the array for a node that is not read from the file.

You may recall that the computational strategy reserves an element in the array for every possible node of a tree for any given height and order.  When loading the tree you may use Java class function(s) (Arrays.binarySearch()?) to find the index of the parent to facilitate your calculation of the indexes of the children.

The second file, Lab3InputFile2.txt, contains a list of search integers.  After you have read the tree nodes from Lab3InputFile1 and loaded them into your array, you should write your own function to use the search functionality of the binary tree to determine if the search integers are in the tree or not.

You should print a clearly labeled, formatted report of your results containing each search integer and whether it is found or not.  If it is found, include the index of the binary tree array and the level of the tree.

Your program should work for input files of any size representing a tree no greater than height 5.  Your program should not be dependent on the sequence of the search list.  When executing your program In NetBeans, the working directory is the directory of your project.  To facilitate us evaluating your work, add the two data files to your project directory.  Do not change the file names.  All internal references to the files in your program should be the file name only.  Do not include the full path.

Submit your work as a .zip file as detailed in the Lab Submission Guidelines document.
