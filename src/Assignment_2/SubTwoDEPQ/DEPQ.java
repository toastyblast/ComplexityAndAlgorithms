package Assignment_2.SubTwoDEPQ;

import java.util.ArrayList;

/**
 * Using the Symmetric Min-Max Heap V1.0 (http://people.cs.nctu.edu.tw/~chengchc/Course_2011_Fall_DS/DS_09_Heaps.pdf)
 */
public class DEPQ {
    private ArrayList<Integer> heap;

    public DEPQ() {
        heap = new ArrayList<>();
    }

    public ArrayList<Integer> getHeap() {
        return heap;
    }

    public boolean isEmpty() {
        return (heap.get(1) == null && heap.get(2) == null);
    }

    public int size() {
        //TODO: Return the amount of nodes in this SMMH (So NOT the HEIGHT)... (Excluding or including the root node?)
        return 0;
    }

    public int getMin() {
        if (heap.get(1) != null) {
            return heap.get(1);
        }
        else {
            return 0;
        }
    }

    public int getMax() {
        if (heap.get(2) != null) {
            return heap.get(2);
        }
        else {
            return 0;
        }
    }

    public void put(int input) {
        //TODO: Add a value to the SMMH
    }

    public void removeMin() {
        //TODO: Remove the left child of the root (starting) node.
    }

    public void removeMax() {
        //TODO: Remove the right child of the root (starting) node.
    }
}
