package Assignment_2.SubTwoDEPQ;

/**
 * Using the Symmetric Min-Max Heap V1.0 (http://people.cs.nctu.edu.tw/~chengchc/Course_2011_Fall_DS/DS_09_Heaps.pdf)
 */
public class DEPQ {
    private final Node startingNode;

    public DEPQ() {
        startingNode = new Node();
    }

    public boolean isEmpty() {
        return (startingNode.getLeftChild() == null && startingNode.getRightChild() == null);
    }

    public int size() {
        //TODO: Return the amount of nodes in this SMMH (So NOT the HEIGHT)... (Excluding or including the root node?)
        return 0;
    }

    public int getMin() {
        Node leftChild = startingNode.getLeftChild();

        if (leftChild != null) {
            return leftChild.getValue();
        }
        else {
            return 0;
        }
    }

    public int getMax() {
        Node rightChild = startingNode.getRightChild();

        if (rightChild != null) {
            return rightChild.getValue();
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
