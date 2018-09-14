

public final class InputData {
    // I feel like if I make any of these public, I will loose points, even though there
    // are no invarients to keep
    private String mName;
    private String mID;
    private int mHours;

    public InputData(String name, String id, int hours) {
        mName = name;
        mID = id;
        mHours = hours;
    }

    public String getName() {
        return mName;
    }

    public String getID() {
        return mID;
    }

    public int getHours() {
        return mHours;
    }

    public void setName(String s) {
        mName = s;
    }

    public void setID(String s) {
        mID = s;
    }

    public void setHours(int i) {
        mHours = i;
    }

    public String toString() {
        return mName + "-" + mID + " is " + mHours + " credit hours";
    }
}