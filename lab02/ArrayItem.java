/*
 * This class holds an Array Item as defined in the README for this lab
 *
 * @author Aryan Gupta
 * @date 2018/10/9
 */

import java.lang.Integer;

class ArrayItem implements Comparable<ArrayItem> { // CRTP in Java? **Mindblown**
	private String color; ///< String name of the color
	private int ID; ///< Id of the color as an int

	/*
	 * Constructs the ArrayItem with default parameters
	 * @param color The string representation of the color
	 * @param id The integer representaion of the color (aka ID)
	 */
	public ArrayItem(String color, int id) {
		this.color = color;
		this.ID = id;
	}

	/*
	 * Getters and setters for the integer representation of the color
	 */
	public void setID(int id) { this.ID = id; }
	public int getID() { return this.ID; }

	/*
	 * Getters and setters for the integer representation of the color
	 */
	public void setColor(String str) { color = str; }
	public String getColor() { return color; }

	/*
	 * Compares 2 ArrayItem together. First uses the String representation,
	 * if the string matches, then falls back to using the ID
	 *
	 * @param other The other ArrayItem to compare with
	 * @return >0 if other is bigger, =0 if other is the same, <0 if other is smaller
	 */
	public int compareTo(ArrayItem other) {
		int cmp = color.compareTo(other.color);
		if (cmp == 0) // If our strings are equal, fallback to comparing the ID
			cmp = Integer.compare(ID, other.ID); // EXTRA POINTS
		return cmp;
	}

	/*
	 * Converts this to a string representation <COLOR_STR>:<ID>
	 * @return The string representation of this object
	 */
	public String toString() {
		return color + ":" + Integer.toString(ID);
		// return Integer.toString(ID) + ":" + color;
	}
}