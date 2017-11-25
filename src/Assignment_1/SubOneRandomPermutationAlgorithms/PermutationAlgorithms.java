package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.util.Arrays;

/**
 * Class that includes three permutation unsorted integer arrays generating methods. Also includes a method to check if
 * the arrays are actually permuted or not, for DEBUG purposes.
 * <p>
 * Also includes a QuickSort method for arrays, taken from www.programcreek.com (more on this in the JavaDoc of that method).
 *
 * @author C&A-p 4 ~ Martin S. Slavov (435666) and Yoran Kerbusch (430818)
 * Saxion Enschede HBO-IT International - Y2Q2 24/11/2017
 */
public class PermutationAlgorithms {
    private int amountOfNumbers;
    private boolean doPermuteCheck;

    public PermutationAlgorithms(int N, boolean doPermuteCheck) {
        this.amountOfNumbers = N;
        this.doPermuteCheck = doPermuteCheck;
    }

    /**
     * Method that generates an unsorted array of integers according to permutation rules. The array will include the
     * integers 1 up till amountOfNumbers, in a random order.
     * <p>
     * This method checks if the generated number is unique by comparing it to every value already put in the array.
     * If not, it generates a new number and repeats this checking. Else it adds the number to the array.
     *
     * @return int[] array with random integers between 1 and amountOfNumbers, generated according to permutation rules.
     */
    public int[] algorithmOne() {
        int[] ints = new int[amountOfNumbers];
        int newNumber = -1;

        for (int i = 0; i < ints.length; i++) {
            boolean isUnique = false;

            while (!isUnique) {
                newNumber = generateRandomNumber();
                isUnique = true;

                for (int j = 0; j < i; j++) {
                    //Go through every already added value in the array ints.
                    if (ints[j] == newNumber) {
                        //If the newly generated random number is already in the array, it's not unique: start the
                        // while-loop again to generate a new number.
                        isUnique = false;
                        break;
                    }
                }
            }

            ints[i] = newNumber;
        }

        if (doPermuteCheck) {
            //If the user wants to do the DEBUG permute check, then call the helper method for that.
            doValidation(ints);
        }

        return ints;
    }

    /**
     * Method that generates an unsorted array of integers according to permutation rules. The array will include the
     * integers 1 up till amountOfNumbers, in a random order.
     * <p>
     * This method has a second array of "false" booleans. Once a new number is generated, this array is checked at the
     * index that's equal to the value of the newly generated number (- 1 due to array indexing starting at 0).
     * If it's "true", the newly generated number is not unique, and a new one is generated, then repeating the checks.
     * If it's "false", the newly generated integer is unique and  it's set to "true", adding the newly generated
     * integer is added to the array of integers.
     *
     * @return int[] array with random integers between 1 and amountOfNumbers, generated according to permutation rules.
     */
    public int[] algorithmTwo() {
        int[] ints = new int[amountOfNumbers];
        //All values in an undefined boolean array are standard set to "false", which we need in our case.
        boolean[] used = new boolean[amountOfNumbers];
        int newNumber = -1;

        for (int i = 0; i < ints.length; i++) {
            boolean isUnique = false;

            while (!isUnique) {
                newNumber = generateRandomNumber();
                isUnique = true;

                if (used[(newNumber - 1)] == true) {
                    //If the used array has true on the index that is the same as this value, it means the number is
                    // already in the ints array. Therefore, it's not permuted and not unique, so a new number has to be generated.
                    isUnique = false;
                }
            }

            used[(newNumber - 1)] = true;
            ints[i] = newNumber;
        }

        if (doPermuteCheck) {
            //If the user wants to do the DEBUG permute check, then call the helper method for that.
            doValidation(ints);
        }

        return ints;
    }

    /**
     * Method that generates an unsorted array of integers according to permutation rules. The array will include the
     * integers 1 up till amountOfNumbers, in a random order.
     * <p>
     * This method takes the i value of the for-loop and puts i + 1 on the end of the array. It then picks a random
     * index between 0 and i and gets the value of that index. It then swaps this value with the new value.
     *
     * @return int[] array with random integers between 1 and amountOfNumbers, generated according to permutation rules.
     */
    public int[] algorithmThree() {
        int[] ints = new int[amountOfNumbers];

        for (int i = 0; i < ints.length; i++) {
            //Set the value of the latest i (index) to i + 1 (since we go from 1 till N instead of from 0 till N - 1).
            ints[i] = (i + 1);

            //Pick a random index between 0 and i.
            int randomIndex = (int) (Math.random() * i);

            //Get the value at the random index.
            int temp = ints[randomIndex];
            //Make the random index the new i.
            ints[randomIndex] = (i + 1);
            //Then make i the old value of the random index.
            ints[i] = temp;
        }

        if (doPermuteCheck) {
            //If the user wants to do the DEBUG permute check, then call the helper method for that.
            doValidation(ints);
        }

        return ints;
    }

    /**
     * Helper method that generates a random integer between 1 and amountOfNumbers (given maximum array size).
     *
     * @return int: a random integer between 1 and amountOfNumbers.
     */
    private int generateRandomNumber() {
        return (int) (Math.random() * amountOfNumbers) + 1;
    }

    /**
     * Helper method that handles validating that the array is permuted and prints out its results.
     *
     * @param ints is the (hopefully) permuted list to be checked on that fact.
     */
    private void doValidation(int[] ints) {
        int[] tempInts = Arrays.copyOf(ints, amountOfNumbers);

        System.out.println("PERMUTE CHECKER - Array is permuted: " + uniqueChecker(tempInts));
//        System.out.println("DEBUG - Sorted list for permuted check: " + Arrays.toString(tempInts));
    }

    /**
     * Method that first quicksorts the array using a helper class and then checks if all values within the array are
     * unique, aka permuted. The sorted array will NOT be given to the user, they'll still receive the randomized array.
     * Included mainly as a test for DEBUG of new algorithms.
     *
     * @param arr is the array to be checked.
     * @return true if the array is permuted (no duplicated) or false if it is not (there are duplicated, in which case
     * the program is broken.)
     */
    private boolean uniqueChecker(int[] arr) {
        quickSort(arr, 0, (amountOfNumbers - 1));

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                //Check if the last value is equal to the current value. If it is, the array is not permuted.
                return false;
            }
            if (arr[i] != (arr[i - 1] + 1)) {
                //Check if the last value equals the current one when increased by 1. If not, the array is not permuted.
                return false;
            }
        }

        return true;
    }

    /**
     * A recursive quicksorting algorithm for arrays taken from www.programcreek.com. The team decided to do this after they
     * accidentally made a quicksort for ArrayLISTs, and at that point couldn't be bothered to transform everything anymore.
     *
     * @param array is the array that is to be quicksorted.
     * @param low   is the left point of the pivot, so that it is known where to start the array to be sorted.
     * @param high  is the right point of the pivot, so that it is known where to end the array to be sorted.
     */
    private static void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0) {
            //Base case, the array I've recursively been given is empty.
            return;
        }
        if (low >= high) {
            //Base case, the left index (low) is equal or higher than the right index (high), meaning there's only one
            // integer in the array, or it's empty.
            return;
        }

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;

        while (i <= j) {

            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts, the array left of the pivot and the array right of the pivot.
        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }
    }
}
