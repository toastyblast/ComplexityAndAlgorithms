package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.util.Arrays;

public class PermutationAlgorithms {
    private int amountOfNumbers;
    private boolean doPermuteCheck;

    public PermutationAlgorithms(int N, boolean doPermuteCheck) {
        this.amountOfNumbers = N;
        this.doPermuteCheck = doPermuteCheck;
    }

    public int[] algorithmOne() {
        int[] ints = new int[amountOfNumbers];
        int newNumber = -1;

        for (int i = 0; i < ints.length; i++) {
            boolean isUnique = false;

            while (!isUnique) {
                newNumber = generateRandomNumber();
                isUnique = true;

                for (int j = 0; j < i; j++) {
                    if (ints[j] == newNumber) {
                        isUnique = false;
                        break;
                    }
                }
            }

            ints[i] = newNumber;
        }

        int[] tempInts = Arrays.copyOf(ints, amountOfNumbers);

        if (doPermuteCheck) {
            System.out.println("PERMUTE CHECKER - Array is permuted: " + uniqueChecker(tempInts));
//            System.out.println("DEBUG - Sorted list for permuted check: " + Arrays.toString(tempInts));
        }

        return ints;
    }

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
                    isUnique = false;
                }
            }

            used[(newNumber - 1)] = true;
            ints[i] = newNumber;
        }

        int[] tempInts = Arrays.copyOf(ints, amountOfNumbers);

        if (doPermuteCheck) {
            System.out.println("PERMUTE CHECKER - Array is permuted: " + uniqueChecker(tempInts));
//            System.out.println("DEBUG - Sorted list for permuted check: " + Arrays.toString(tempInts));
        }

        return ints;
    }

    public int[] algorithmThree() {
        int[] ints = new int[amountOfNumbers];

        //...

        int[] tempInts = Arrays.copyOf(ints, amountOfNumbers);

        if (doPermuteCheck) {
            System.out.println("PERMUTE CHECKER - Array is permuted: " + uniqueChecker(tempInts));
//            System.out.println("DEBUG - Sorted list for permuted check: " + Arrays.toString(tempInts));
        }

        return ints;
    }

    public int generateRandomNumber() {
        return (int) (Math.random() * amountOfNumbers) + 1;
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
    public boolean uniqueChecker(int[] arr) {
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
    public static void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0) {
            return;
        }
        if (low >= high) {
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

    //Other methods...
}
