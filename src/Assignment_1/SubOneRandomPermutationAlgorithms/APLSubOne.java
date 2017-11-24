package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.util.Arrays;

public class APLSubOne {
    private static final int ALGORITHM_TO_RUN = 1;

    public static void main(String[] args) {
        new APLSubOne().run();
    }

    public void run() {
        PermutationAlgorithms permutationAlgorithms = new PermutationAlgorithms(6);

        switch (ALGORITHM_TO_RUN) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    System.out.println("Randomly generated permuted array: " + Arrays.toString(permutationAlgorithms.algorithmOne()));
                }

                break;
            case 2:
                //Algorithm two...
                break;
            case 3:
                //Algorithm three...
                break;
            default:
                System.out.println("Invalid algorithm code has been given.");
                break;
        }
    }
}
