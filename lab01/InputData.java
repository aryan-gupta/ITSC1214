/* 
 * This class stores one set of input data. Please see README.md for more
 * details on data. 
 * @author Aryan Gupta
 * @version 0.1
 */

public final class InputData {
    // I feel like if I make any of these public, I will loose points, even though there
    // are no invarients to keep
    private String mName;
    private String mID;
    private int mHours;

    /* 
     * C'tor: Initilizes the class with the parameters given
     * @param name The name of the class
     * @param id The ID of the class
     * @param hours The number of credit hours the class is
     */
    public InputData(String name, String id, int hours) {
        mName = name;
        mID = id;
        mHours = hours;
    }

    /* 
     * Gets the name of the class
     * @return The name
     */
    public String getName() {
        return mName;
    }

    /* 
     * Gets the ID of the class
     * @return The ID
     */
    public String getID() {
        return mID;
    }

    /* 
     * Gets the credit hours of the class
     * @return The hours
     */
    public int getHours() {
        return mHours;
    }

    /* 
     * Sets the name of the class
     * @param The name
     */
    public void setName(String s) {
        mName = s;
    }

    /* 
     * Sets the ID of the class
     * @param The ID
     */
    public void setID(String s) {
        mID = s;
    }

    /* 
     * Sets the credit hours of the class
     * @param The hours
     */
    public void setHours(int i) {
        mHours = i;
    }

    /* 
     * Gets the credit hours of the class
     * @return The hours
     */
    public String toString() {
        return mName + "-" + mID + " is " + mHours + " credit hours";
    }
}