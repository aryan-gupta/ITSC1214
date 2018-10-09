import java.lang.Integer;

class ArrayItem implements Comparable<ArrayItem> { // CRTP in Java? **Mindblown**
	private String color;
	private int ID;

	public ArrayItem(String color, int id) {
		this.color = color;
		this.ID = id;
	}

	public void setID(int id) { this.ID = id; }
	public int getID() { return this.ID; }

	public void setColor(String str) { color = str; }
	public String getColor() { return color; }

	public int compareTo(ArrayItem other) {
		int cmp = Integer.compare(ID, other.ID);
		if (cmp == 0) // If our ID's are equal, fallback to comparing the Strings
			cmp = color.compareTo(other.color); // EXTRA POINTS
		return cmp;
	}

	public String toString() {
		return Integer.toString(ID) + ":" + color;
	}
}