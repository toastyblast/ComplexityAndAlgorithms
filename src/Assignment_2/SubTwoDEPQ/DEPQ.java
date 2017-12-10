package Assignment_2.SubTwoDEPQ;

import java.util.ArrayList;

/**
 * A DEPQ that works according to the rules set by the Symmetric Min-Max Heap V1.0
 * (http://people.cs.nctu.edu.tw/~chengchc/Course_2011_Fall_DS/DS_09_Heaps.pdf)
 *
 * @author Yoran Kerbusch & Martin S. Slavov
 */
public class DEPQ {
    private ArrayList<Node> depq;

    public DEPQ() {
        depq = new ArrayList<>();
        //Set the root node to highest priority with a value of zero, so it's always at the top.
        depq.add(new Node(0, 0));
    }

    /**
     * Method that returns if the SMMH DEPQ is empty or not. Does not count the empty root node, as this one is always present.
     *
     * @return true if the SMMH DEPQ is empty (besides the empty root node) or false if there are one of more actual nodes.
     */
    public boolean isEmpty() {
        return (depq.size() == 1);
    }

    /**
     * Returns the complete size of the SMMH DEPQ, including the empty root node.
     *
     * @return int the size of the SMMH DEPQ
     */
    public int size() {
        return depq.size();
    }

    /**
     * Returns the value of the left child node of the empty root node, which is the minimum value in the SMMH DEPQ.
     *
     * @return int the value of the left child node ("root" of the min-heap) of the empty root.
     */
    public int getMin() {
        if (depq.size() >= 2) {
            //Get the value left of the root node (index 0), so index 1.
            return depq.get(1).getValue();
        }

        return 0;
    }

    /**
     * Returns the value of the right child node of the empty root node, which is the maximum value in the SMMH DEPQ.
     *
     * @return int the value of the right child node ("root" of the max-heap) of the empty root.
     */
    public int getMax() {
        if (depq.size() >= 3) {
            //Get the value right of the root node (index 0), so index 2.
            return depq.get(2).getValue();
        }

        return 0;
    }

    /**
     * Adds a new node to the SMMH DEPQ, sorting it into the right place of said heap.
     *
     * @param priority int is the priority to be given to the new node. Must be at least 1.
     * @param value int is a value that can be given to the node, for purely aesthetic reasons.
     */
    public void put(int priority, int value) {
        if (priority >= 1) {
            Node nodeToAdd = new Node(priority, value);

            depq.add(nodeToAdd);

            if (depq.size() >= 3) {
                //This means the newly added node is at least not the top node of the min-heap (aka the left child of root),
                // meaning we have to compare its value with one on the other heap, and if it's smaller, swap it.
                int indexNew = (depq.size() - 1);

                if (isInMaxHeap(indexNew)) {
                    //The new node has been placed in the max-heap, aka the right part of the root node.
                    int indexCorrespondingNode = getCorrespondingMinNodeIndex(indexNew);
                    Node correspondingNode = depq.get(indexCorrespondingNode);

                    if (priority < correspondingNode.getKey()) {
                        //Swap the newly added node on the max-heap with its corresponding node on the min-heap
                        indexNew = indexCorrespondingNode;
                        swapNodes(indexNew, nodeToAdd, indexCorrespondingNode, correspondingNode);

                        minPercolateUp(indexNew, nodeToAdd);
                    } else {
                        maxPercolateUp(indexNew, nodeToAdd);
                    }
                } else {
                    //The new node has been placed in the min-heap, aka the left part of the root node.
                    int indexCorrespondingNode = getCorrespondingMaxNodeIndex(indexNew);
                    Node correspondingNode = depq.get(indexCorrespondingNode);

                    if (priority > correspondingNode.getKey()) {
                        //Swap the newly added node on the min-heap with its corresponding node on the max-heap
                        indexNew = indexCorrespondingNode;
                        swapNodes(indexNew, nodeToAdd, indexCorrespondingNode, correspondingNode);

                        maxPercolateUp(indexNew, nodeToAdd);
                    } else {
                        minPercolateUp(indexNew, nodeToAdd);
                    }
                }
            }
        } else {
            System.out.println("ERROR: Cannot put a node with priority of 0 or less in the SMMH DEPQ. Please enter a priority of at least 1.");
        }
    }

    public void removeMin() {
        if (depq.size() >= 2) {
            //TODO: Remove the left child of the root (starting) node and rebuild the heap left of root (if the min node had children, that is.)
            //...
        }
    }

    public void removeMax() {
        if (depq.size() >= 3) {
            //TODO: Remove the right child of the root (starting) node and rebuild the heap right of root (if the max node had children, that is.)
            //...
        }
    }

    public void changePriority(int index, int newPriority) {
        if (index >= 2 && index <= depq.size()) {
            if (newPriority >= 1) {
                //TODO: Check if the priority is different to the old one, then move around the node accordingly
                //...
            } else {
                System.out.println("ERROR: Cannot change a node's priority to 0 or less in the SMMH DEPQ. Please enter a new priority of at least 1.");
            }
        } else {
            System.out.println("ERROR: Cannot change the priority of the root node (index 1) or non-existent nodes (given index > size of depq).");
        }
    }

    /**
     * Helper method that checks if the given index is located in the min-heap (left side of empty root) or in the
     * max-heap (right side of empty root).
     *
     * @param index int is the index to be checked if it falls in the min- or max-heap of the SMMH DEPQ
     * @return boolean true if the node at given index is in the max-heap, false if it's not.
     */
    private boolean isInMaxHeap(int index) {
        double calculation = (3 * Math.pow(2, Math.floor(Math.log(index) / Math.log(2)) - 1) - 1);

        if (index > calculation) {
            return true;
        }

        return false;
    }

    private int getCorrespondingMinNodeIndex(int index) {
        //TODO: Get the index of the node corresponding to the one at the given index.
        //...
        return 0;
    }

    private int getCorrespondingMaxNodeIndex(int index) {
        //TODO: Get the index of the node corresponding to the one at the given index.
        //...
        return 0;
    }

    /**
     * Helper method that swaps two nodes in the SMMH DEPQ.
     * !!!WARNING!!! Does not rebuild the heap after this, as all methods using this one should be doing that themselves.
     *
     * @param indexFirstNode int is the index of the first node.
     * @param firstNode Node is the first node to be swapped with the other.
     * @param indexSecondNode int is the index of the second node.
     * @param secondNode Node is the second node that needs to be swapped with the first.
     */
    private void swapNodes(int indexFirstNode, Node firstNode, int indexSecondNode, Node secondNode) {
        depq.set(indexSecondNode, firstNode);
        depq.set(indexFirstNode, secondNode);
    }

    /**
     * Helper method that compares the given node IN A MIN-HEAP with their parent and swaps them around if needed.
     *
     * @param indexNode int is the index of the given node.
     * @param node Node is the node that needs to be compared with its parent for possible swapping.
     */
    private void minPercolateUp(int indexNode, Node node) {
        if (indexNode != 2 && indexNode != 3) {
            Node parentNode = getParentNode(indexNode);

            if (node.getKey() < parentNode.getKey()) {
                int parentIndex = depq.indexOf(parentNode);

                swapNodes(indexNode, node, parentIndex, parentNode);
                minPercolateUp(parentIndex, node);
            }
        }
    }

    /**
     * Helper method that compares the given node IN A MAX-HEAP with their parent and swaps them around if needed.
     *
     * @param indexNode int is the index of the given node.
     * @param node Node is the node that needs to be compared with its parent for possible swapping.
     */
    private void maxPercolateUp(int indexNode, Node node) {
        if (indexNode != 2 && indexNode != 3) {
            Node parentNode = getParentNode(indexNode);

            if (node.getKey() > parentNode.getKey()) {
                int parentIndex = depq.indexOf(parentNode);

                swapNodes(indexNode, node, parentIndex, parentNode);
                maxPercolateUp(parentIndex, node);
            }
        }
    }
    
    private Node getParentNode(int indexChild) {
        //TODO: Get the priority of the parent of the given node
        //...
        return null;
    }

    private void minHeapify(int indexNode) {
        //TODO: Restore the min-heap, aka the heap left of the empty root node.
        //...
    }

    private void maxHeapify(int indexNode) {
        //TODO: Restore the max-heap, aka the heap right of the empty root node.
        //...
    }

    public String simpleArrayToString() {
        return depq.toString();
    }
}
