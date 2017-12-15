package Assignment_2.SubTwoDEPQ;

/**
 * A Node Object that has a priority (key) needed for the DEPQ. Also has a value, which are only there to give the Node
 * some purpose, could be used to store text or other objects instead, if wanted.
 */
public class Node {
    private int key, value;

    public Node() {
        //Empty constructor.
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Priority: " + key + " & Value: " + value;
    }
}
