package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.util.Arrays;

/**
 * APL for Complexity & Algorithms assignment 1.1, which is the one this duo chose.
 * They are aware that there's a lot of duplicate code in the switches, but they do not know a quick fix for this, as
 * timers have to stay right before and after the point that the algorithm is performed, additionally, all the output is
 * somewhat different per algorithm for clarity's sake, in the way it's currently done.
 *
 * @author C&A-p 4 ~ Martin S. Slavov (435666) and Yoran Kerbusch (430818)
 * Saxion Enschede HBO-IT International - Y2Q2 24/11/2017
 */
public class APLSubOne {
    //Use this value to set which algorithm should be used. Number corresponds to the question in assignment 1.1
    private static final int ALGORITHM_TO_RUN = 1;

    //Change this number to set how many numbers will be generated. The array will include all number between 1 and this value.
    //Algorithm 1 N = 5000, 10000, 20000, 50000, 100000 and 200000 numbers.
    //Algorithm 2 N = 100000, 200000, 500000, 1000000, 5000000 and 10000000 numbers.
    //Algorithm 3 N = 2000000, 5000000, 10000000, 20000000, 50000000, 100000000 numbers.
    private static final int AMOUNT_OF_NUMBERS = 5000;

    //Change this value to set how many times the algorithm that was chosen will be run.
    private static final int NUMBER_OF_ALGORITHM_RUNS = 10;

    //DEBUG OPTION: This will cause the system to check if the array is permuted, aka only filled with unique numbers
    // and all values from 1 and the chosen amount of numbers are present. Set to "true" if you want it to check permutation.
    //PLEASE NOTE: THIS WILL INCREASE THE TIME TAKEN BY THE ALGORITHM TO RUN!
    private static final boolean CHECK_ARRAY_IS_PERMUTED = false;

    public static void main(String[] args) {
        if (ALGORITHM_TO_RUN <= 0 || ALGORITHM_TO_RUN >= 4) {
            System.out.println("ERROR - Invalid algorithm code requested: " + AMOUNT_OF_NUMBERS + " does not exist.");
        }
        else if (AMOUNT_OF_NUMBERS <= 0) {
            System.out.println("ERROR - Invalid amount of integers requested: " + AMOUNT_OF_NUMBERS);
        }
        else if (NUMBER_OF_ALGORITHM_RUNS <= 0) {
            System.out.println("ERROR - Invalid amount of times to run given: " + NUMBER_OF_ALGORITHM_RUNS);
        }
        else {
            new APLSubOne().run();
        }
    }

    public void run() {
        PermutationAlgorithms permutationAlgorithms = new PermutationAlgorithms(AMOUNT_OF_NUMBERS, CHECK_ARRAY_IS_PERMUTED);

        System.out.println("Are the arrays being checked if they're valid (permuted)? " + CHECK_ARRAY_IS_PERMUTED + ".\n");

        long totalTime = 0;
        long averageTime;

        switch (ALGORITHM_TO_RUN) {
            case 1:
                //User wants to run algorithm one.
                for (int i = 1; i <= NUMBER_OF_ALGORITHM_RUNS; i++) {
                    //Run the algorithm for the amount of times specified by the user.
                    System.out.println("Sub-Assignment 1.1 - ALGORITHM ONE Run #" + i);

                    long startTime = System.currentTimeMillis();
                    System.out.println("Randomly generated permuted array: " + Arrays.toString(permutationAlgorithms.algorithmOne()));
                    long endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Time taken to generate the array: " + endTime + " ms.\n");

                    totalTime += endTime;
                }

                averageTime = totalTime / NUMBER_OF_ALGORITHM_RUNS;
                System.out.println("ALGORITHM ONE - Average time taken over " + NUMBER_OF_ALGORITHM_RUNS + " number of runs: " + averageTime + " ms.");

                break;
            case 2:
                //User wants to run algorithm two.
                for (int i = 1; i <= NUMBER_OF_ALGORITHM_RUNS; i++) {
                    //Run the algorithm for the amount of times specified by the user.
                    System.out.println("Sub-Assignment 1.2 - ALGORITHM TWO Run #" + i);

                    long startTime = System.currentTimeMillis();
                    System.out.println("Randomly generated permuted array: " + Arrays.toString(permutationAlgorithms.algorithmTwo()));
                    long endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Time taken to generate the array: " + endTime + " ms.\n");

                    totalTime += endTime;
                }

                averageTime = totalTime / NUMBER_OF_ALGORITHM_RUNS;
                System.out.println("ALGORITHM TWO - Average time taken over " + NUMBER_OF_ALGORITHM_RUNS + " number of runs: " + averageTime + " ms.");

                break;
            case 3:
                //User wants to run algorithm three.
                for (int i = 1; i <= NUMBER_OF_ALGORITHM_RUNS; i++) {
                    //Run the algorithm for the amount of times specified by the user.
                    System.out.println("Sub-Assignment 1.3 - ALGORITHM THREE Run #" + i);

                    long startTime = System.currentTimeMillis();
                    System.out.println("Randomly generated permuted array: " + Arrays.toString(permutationAlgorithms.algorithmThree()));
                    long endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Time taken to generate the array: " + endTime + " ms.\n");

                    totalTime += endTime;
                }

                averageTime = totalTime / NUMBER_OF_ALGORITHM_RUNS;
                System.out.println("ALGORITHM THREE - Average time taken over " + NUMBER_OF_ALGORITHM_RUNS + " number of runs: " + averageTime + " ms.");

                break;
            default:
                System.out.println("Invalid algorithm code has been given.");
                break;
        }
    }
}
