import java.util.ArrayList;

public class Logic {
    //Instance Variables
    private ArrayList<Integer> storedLowerBound;
    private ArrayList<Integer> storedHigherBound;
    private ArrayList<Integer> storedIndexPosition;


    //Constructor
    Logic() {
        storedLowerBound = new ArrayList<Integer>();
        storedHigherBound = new ArrayList<Integer>();
        storedIndexPosition = new ArrayList<Integer>();

    }

    public int interpolationSearch(ArrayList<Integer> givenSearchArray, int key) {
        //Initializing variables
        int lowIndex = 0;
        int highIndex = givenSearchArray.size() - 1;
        int indexPosition = 0;

        //Search until highIndex and lowIndex is equal to each other or the key is found
        while (key >= givenSearchArray.get(lowIndex) && key <= givenSearchArray.get(highIndex) && !givenSearchArray.get(highIndex).equals(givenSearchArray.get(lowIndex))) {

            //Formula for calculating the position
            indexPosition = lowIndex + ((key - givenSearchArray.get(lowIndex)) * (highIndex - lowIndex) / (givenSearchArray.get(highIndex) - givenSearchArray.get(lowIndex)));

            //Storing the results into the arraylist for future use
            storedLowerBound.add(lowIndex);
            storedHigherBound.add(highIndex);
            storedIndexPosition.add(indexPosition);


            //Check if the key is found
            if (givenSearchArray.get(indexPosition) == key)
                return indexPosition;

                //If the current value is on the right side move the highIndex
            else if (givenSearchArray.get(indexPosition) > key)
                highIndex = indexPosition - 1;

                //If the current value is on the left side move the lowIndex
            else
                lowIndex = indexPosition + 1;
        }

        //Return -99999 to indicate value not found
        return -999999;
    }

    public void displayArray() {
        for (int counter : storedLowerBound) {
            System.out.println(counter + " ");
        }
    }

    public ArrayList<Integer> getStoredLowerBound() {
        return storedLowerBound;
    }

    public ArrayList<Integer> getStoredHigherBound() {
        return storedHigherBound;
    }

    public ArrayList<Integer> getStoredIndexPosition() {
        return storedIndexPosition;
    }
}
