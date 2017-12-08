package Assignment_2.SubTwoDEPQ;

public class Node {
    private int value;
    private Node leftChild = null;
    private Node rightChild = null;

    public Node() {
        //Empty constructor.
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    //Etc methods...
}
