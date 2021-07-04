import java.util.ArrayList;

public class Logic {
    //Instance Variables
    private ArrayList<Integer> storedLowerBound;
    private ArrayList<Integer> storedHigherBound;
    private ArrayList<Integer> storedIndexPosition;
    private int indexCount;

    //Constructor
    Logic() {
        storedLowerBound = new ArrayList<Integer>();
        storedHigherBound = new ArrayList<Integer>();
        storedIndexPosition = new ArrayList<Integer>();
        indexCount = 0;
    }

    public int interpolationSearch(int[] givenSearchArray, int key) {
        //Initializing variables
        int lowIndex = 0;
        int highIndex = givenSearchArray.length - 1;
        int indexPosition = 0;

        //Search until highIndex and lowIndex is equal to each other or the key is found
        while (key >= givenSearchArray[lowIndex] && key <= givenSearchArray[highIndex] && givenSearchArray[highIndex] != givenSearchArray[lowIndex]) {

            //Formula for calculating the position
            indexPosition = lowIndex + ((key - givenSearchArray[lowIndex]) * (highIndex - lowIndex) / (givenSearchArray[highIndex] - givenSearchArray[lowIndex]));

            //Storing the results into the arraylist for future use
            storedLowerBound.add(lowIndex);
            storedHigherBound.add(highIndex);
            storedIndexPosition.add(indexPosition);
            ++indexCount;

            //Check if the key is found
            if (givenSearchArray[indexPosition] == key)
                return indexPosition;

                //If the current value is on the right side move the highIndex
            else if (givenSearchArray[indexPosition] > key)
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
}
