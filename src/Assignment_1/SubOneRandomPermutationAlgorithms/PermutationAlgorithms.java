package Assignment_1.SubOneRandomPermutationAlgorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PermutationAlgorithms {
    private int amountOfNumbers;
    //Variables...

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

    public void checker(){
        
    }

    //Other methods...
}
