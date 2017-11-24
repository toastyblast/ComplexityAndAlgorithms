package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.util.Arrays;

public class PermutationAlgorithms {
    private int amountOfNumbers;

    public PermutationAlgorithms(int n) {
        this.amountOfNumbers = n;

    }

    public int[] algorithmOne(){
        int [] ints = new int[amountOfNumbers];
        int newNumber = -1;

        for (int i = 0 ; i < ints.length ; i++){
            boolean isUnique = false;

            while(!isUnique) {
                newNumber = generateRandomNumber();
                isUnique = true;

                for (int j = 0; j < i ; j++) {
                    if (ints[j] == newNumber){
                        isUnique = false;
                        break;
                    }
                }
            }

            ints[i] = newNumber;
        }

//        System.out.println("DEBUG - Randomly generated array (before permute check): " + Arrays.toString(ints));
        int [] tempInts = Arrays.copyOf(ints, amountOfNumbers);
//        System.out.println("DEBUG - ARRAY IS PERMUTED: " + uniqueChecker(tempInts));
//        System.out.println("DEBUG - Sorted list for permuted check: " + Arrays.toString(tempInts));

        return ints;
    }

    public void algorithmTwo(){
      //...
    }

    public void algorithmThree(){
       //...
    }

    public int generateRandomNumber(){
        return (int)(Math.random() * amountOfNumbers) + 1;
    }

    public boolean uniqueChecker(int[] arr){
        quickSort(arr, 0, (amountOfNumbers - 1));

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                return false;
            }
        }

        return true;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

    //Other methods...
}
