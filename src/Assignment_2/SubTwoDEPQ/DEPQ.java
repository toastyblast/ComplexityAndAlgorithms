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

    /*--- START OF USER METHODS -------------------------------------------------------------------------------------*/

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
     * @param value    int is a value that can be given to the node, for purely aesthetic reasons.
     */
    public void put(int priority, int value) {
        if (priority >= 1) {
            Node nodeToAdd = new Node(priority, value);

            depq.add(nodeToAdd);

            if (depq.size() >= 3) {
                //This means the newly added node is at least not the top node of the min-heap (aka the left child of root),
                // meaning we have to compare its value with one on the other heap, and if it's smaller, swap it.
                int indexNew = depq.size() - 1;

                if (isInMaxHeap(indexNew)) {
                    //The new node has been placed in the max-heap, aka the right part of the root node.
                    int indexCorrespondingNode = getCorrespondingMinNodeIndex(indexNew);
                    Node correspondingNode = depq.get(indexCorrespondingNode);

                    if (priority < correspondingNode.getKey()) {
                        //Swap the newly added node on the max-heap with its corresponding node on the min-heap
                        swapNodes(nodeToAdd, correspondingNode);

                        minPercolateUp(nodeToAdd);
                    } else {
                        maxPercolateUp(nodeToAdd);
                    }
                } else {
                    //The new node has been placed in the min-heap, aka the left part of the root node.
                    int indexCorrespondingNode = getCorrespondingMaxNodeIndex(indexNew);
                    Node correspondingNode = depq.get(indexCorrespondingNode);

                    if (priority > correspondingNode.getKey()) {
                        //Swap the newly added node on the min-heap with its corresponding node on the max-heap
                        swapNodes(nodeToAdd, correspondingNode);

                        maxPercolateUp(nodeToAdd);
                    } else {
                        minPercolateUp(nodeToAdd);
                    }
                }
            }
        } else {
            System.out.println("ERROR: Cannot put a node with priority of 0 or less in the SMMH DEPQ. Please enter a priority of at least 1.");
        }
    }

    /**
     * Method that can be used to remove the minimum priority node of the SMMH DEPQ, which is the left child Node of
     * the root Node. This means that this node is the top of the max-heap of the SMMH DEPQ.
     * It will immediately rebuild the min-heap as well, so that the whole SMMH DEPQ is once more valid.
     */
    public void removeMin() {
        if (depq.size() >= 2) {
            depq.set(1, null);

            //Now percolate the empty node down as many times as possible with the help of this recursive method.
            int indexToFill = minPercolateDown(null);
            //Then we need to swap this now empty node with a filled in one, namely the one at the end of the array
            // (most down right of the tree.)
            int lastNodeIndex = depq.size() - 1;
            Node lastNode = depq.get(lastNodeIndex);

            //Swap the last node in the array with the empty node.
            swapNodes(null, lastNode);

            //Check if the last node isn't this current one, otherwise you will get nullPointer exceptions.
            if (indexToFill != lastNodeIndex) {
                //And then finally check if the node now at the empty node's place still needs to swap places with any parents.
                minPercolateUp(lastNode);
            }

            //And then remove that null "Node" from the SMMH DEPQ/ArrayList.
            depq.remove(lastNodeIndex);
        }
    }

    /**
     * Method that can be used to remove the maximum priority node of the SMMH DEPQ, which is the right child Node of
     * the root Node. This means that this node is the top of the max-heap of the SMMH DEPQ.
     * It will immediately rebuild the min-heap as well, so that the whole SMMH DEPQ is once more valid.
     */
    public void removeMax() {
        if (depq.size() >= 3) {
            depq.set(2, null);

            //Now percolate the empty node down as many times as possible with the help of this recursive method.
            int indexToFill = maxPercolateDown(null);
            //Then we need to swap this now empty node with a filled in one, namely the one at the end of the array
            // (most down right of the tree.)
            int lastNodeIndex = depq.size() - 1;
            Node lastNode = depq.get(lastNodeIndex);

            //Swap the last node in the array with the empty node.
            swapNodes(null, lastNode);

            //Check if the last node isn't this current one, otherwise you will get nullPointer exceptions.
            if (indexToFill != lastNodeIndex) {
                //And then finally check if the node now at the empty node's place still needs to swap places with any parents.
                maxPercolateUp(lastNode);
            }

            //And then remove that null "Node" from the SMMH DEPQ/ArrayList.
            depq.remove(lastNodeIndex);
        }
    }

    public void changePriority(int index, int newPriority) {
        if (index >= 2 && index <= depq.size()) {
            if (newPriority >= 1) {
                index = index - 1;
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
     * Method that gives a simple output for the ArrayList (SMMH DEPQ) that holds all the nodes.
     *
     * @return String from StringBuilder that has a list of all nodes in the SMMH DEPQ Arraylist.
     */
    public String simpleArrayToString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<@> Node #0 - ROOT\n");

        for (int i = 1; i < depq.size(); i++) {
            Node node = depq.get(i);
            sb.append("<-> Node #" + i + " - " + node.toString() + '\n');
        }

        return sb.toString();
    }

    /*--------------------------------------------------------------------------------------- END OF USER METHODS ---*/

    /*--- START OF HELPER METHODS -----------------------------------------------------------------------------------*/

    /**
     * Helper method that checks if the given index is located in the min-heap (left side of empty root) or in the
     * max-heap (right side of empty root).
     *
     * @param index int is the index to be checked if it falls in the min- or max-heap of the SMMH DEPQ
     * @return boolean true if the Node at given index is in the max-heap, false if it's not.
     */
    private boolean isInMaxHeap(int index) {
        //The method needs indexes that start from 1, not 0 like in our array.
        index = index + 1;

        double calculation = (3 * Math.pow(2, Math.floor(Math.log(index) / Math.log(2)) - 1) - 1);

        return index > calculation;
    }

    /**
     * Helper method that returns the index of the min-heap node corresponding to the given index on the max-heap.
     *
     * @param index int is the given index of a Node that must be on the max-heap.
     * @return int index of the corresponding min-heap Node to this max-heap Node.
     */
    private int getCorrespondingMinNodeIndex(int index) {
        //The method needs indexes that start from 1, not 0 like in our array.
        index = index + 1;

        //In this case, there's always a guarantee that there are child nodes in the min-heap on the height
        // corresponding to the height of this node, which means we'll not have to do checks for that.
        int calculation = (int) (index - Math.pow(2, Math.floor(Math.log(index) / Math.log(2)) - 1));

        return (calculation - 1);
    }

    /**
     * Helper method that returns the index of the max-heap Node corresponding to the given index on the min-heap.
     *
     * @param index int must be an index of a Node that is located on the min-heap.
     * @return int index of the corresponding max-heap Node to this min-heap Node.
     */
    private int getCorrespondingMaxNodeIndex(int index) {
        //The method needs indexes that start from 1, not 0 like in our array.
        index = index + 1;

        int calculation = (int) (index + Math.pow(2, Math.floor(Math.log(index) / Math.log(2)) - 1));
        calculation = calculation - 1;

        if (calculation > depq.size()) {
            Node parentNode = getParentNode(calculation);

            return depq.indexOf(parentNode);
        } else {
            return calculation;
        }
    }

    /**
     * Helper method that swaps two nodes in the SMMH DEPQ. Given indexes must be from starting to count from 0,
     * as array indexing starts at said value.
     * !!!WARNING!!! Does not rebuild the heap after this, as all methods using this one should be doing that themselves.
     *
     * @param firstNode  Node is the first node to be swapped with the other.
     * @param secondNode Node is the second node that needs to be swapped with the first.
     */
    private void swapNodes(Node firstNode, Node secondNode) {
        int indexFirstNode = depq.indexOf(firstNode);
        int indexSecondNode = depq.indexOf(secondNode);

        depq.set(indexSecondNode, firstNode);
        depq.set(indexFirstNode, secondNode);
    }

    /**
     * Helper method that compares the given node IN A MIN-HEAP with their parent and swaps them around if needed.
     *
     * @param node Node is the node that needs to be compared with its parent for possible swapping.
     */
    private void minPercolateUp(Node node) {
        int indexNode = depq.indexOf(node);

        if (indexNode != 0 && indexNode != 1 && indexNode != 2) {
            Node parentNode = getParentNode(indexNode);

            if (node.getKey() < parentNode.getKey()) {
                swapNodes(node, parentNode);
                minPercolateUp(node);
            }
        }
    }

    /**
     * Helper method that compares the given node IN A MAX-HEAP with their parent and swaps them around if needed.
     *
     * @param node Node is the node that needs to be compared with its parent for possible swapping.
     */
    private void maxPercolateUp(Node node) {
        int indexNode = depq.indexOf(node);

        if (indexNode != 0 && indexNode != 1 && indexNode != 2) {
            Node parentNode = getParentNode(indexNode);

            if (node.getKey() > parentNode.getKey()) {
                swapNodes(node, parentNode);
                maxPercolateUp(node);
            }
        }
    }

    /**
     * Recursive helper method for percolating a Node in the min-heap downwards, for use with the .removeMin() method.
     *
     * @param minNode Node is the given Node to percolate downwards in the min-heap.
     * @return int the new index of the Node after it has been percolated down as many times as possible.
     */
    private int minPercolateDown(Node minNode) {
        int minIndex = depq.indexOf(minNode);
        //We are rebuilding the min heap after removal of the min node, so swap it with the smallest values each time.
        Node leftMinChild = getLeftChild(minIndex);
        Node rightMaxChild = getRightChild(minIndex);
        int nextMinIndex = minIndex;

        if (leftMinChild != null && rightMaxChild != null) {
            //Compare the two children's priority first.
            if (leftMinChild.getKey() < rightMaxChild.getKey()) {
                //The left child should be on the position of the null node, in this case.
                nextMinIndex = depq.indexOf(leftMinChild);
                swapNodes(minNode, leftMinChild);
            } else {
                //The left child should be on the position of the null node, in this case.
                nextMinIndex = depq.indexOf(rightMaxChild);
                swapNodes(minNode, rightMaxChild);
            }
        } else if (leftMinChild != null) {
            //The left child should be on the position of the null node, in this case.
            nextMinIndex = depq.indexOf(leftMinChild);
            swapNodes(minNode, leftMinChild);
        }
        //NOTE: There cannot be a right child if the left child is null, as nodes should always have another node
        // left of them.

        if (nextMinIndex == minIndex) {
            //BASE CASE: If the nextMinIndex hasn't changed, that means this node has no children anymore. In that
            // case, return this empty node's minIndex.
            return minIndex;
        } else {
            //The Node still had children, so check if you can percolate even further down, if its new minIndex has
            // any children too.
            return minPercolateDown(minNode);
        }
    }

    /**
     * Recursive helper method for percolating a Node in the max-heap downwards, for use with the .removeMax() method.
     *
     * @param maxNode Node is the given node to percolate downwards in the max-heap.
     * @return int the new index of the node after it has been percolated down as many times as possible.
     */
    private int maxPercolateDown(Node maxNode) {
        int maxIndex = depq.indexOf(maxNode);
        //We are rebuilding the max heap after removal of the max Node, so swap it with the smallest values each time.
        Node leftMaxChild = getLeftChild(maxIndex);
        Node rightMaxChild = getRightChild(maxIndex);
        int nextMaxIndex = maxIndex;

        if (leftMaxChild != null && rightMaxChild != null) {
            //Compare the two children's priority first.
            if (leftMaxChild.getKey() > rightMaxChild.getKey()) {
                //The left child should be on the position of the null node, in this case.
                nextMaxIndex = depq.indexOf(leftMaxChild);
                swapNodes(maxNode, leftMaxChild);
            } else {
                //The left child should be on the position of the null node, in this case.
                nextMaxIndex = depq.indexOf(rightMaxChild);
                swapNodes(maxNode, rightMaxChild);
            }
        } else if (leftMaxChild != null) {
            //The left child should be on the position of the null node, in this case.
            nextMaxIndex = depq.indexOf(leftMaxChild);
            swapNodes(maxNode, leftMaxChild);
        }
        //NOTE: There cannot be a right child if the left child is null, as nodes should always have another node
        // left of them.

        if (nextMaxIndex == maxIndex) {
            //BASE CASE: If the nextMaxIndex hasn't changed, that means this node has no children anymore. In that
            // case, return this empty node's maxIndex.
            return maxIndex;
        } else {
            //The Node still had children, so check if you can percolate even further down, if its new maxIndex has
            // any children too.
            return maxPercolateDown(maxNode);
        }
    }

    /**
     * Helper method that returns the parent Node of a Node at the given index, as long as the
     * given index = 0 < index <= depq.size - 1
     *
     * @param indexChild int is the index of the Node the calling method wants the parent Node of.
     * @return Node is the parent Node of the given child Node index.
     */
    private Node getParentNode(int indexChild) {
        //The method needs indexes that start from 1, not 0 like in our array, so temporarily increase the given array by 1.
        indexChild++;

        int indexParent = (int) (Math.floor((indexChild) / 2));

        return depq.get(indexParent - 1);
    }

    /**
     * Helper method that returns the left child Node of the Node with the given index.
     * Index must be from between 0 till depq.size - 1.
     *
     * @param parentIndex int is the index of the parent Node we want to get the left child of.
     * @return Node the left child of the parent Node (at the given index).
     */
    private Node getLeftChild(int parentIndex) {
        //The method needs indexes that start from 1, not 0 like in our array, so temporarily increase the given array by 1.
        parentIndex++;
        //Normally the formula would be lc = 2 * i, but we have to -1 due to array indexing starting at 0.
        int leftChildIndex = (2 * parentIndex) - 1;

        if (!(leftChildIndex >= depq.size())) {
            return depq.get(leftChildIndex);
        }

        return null;
    }

    /**
     * Helper method that returns the right child Node of the Node with the given index.
     * Index must be from between 0 till depq.size - 1.
     *
     * @param parentIndex int is the index of the parent Node we want to get the right child of.
     * @return Node the right child of the parent Node (at the given index).
     */
    private Node getRightChild(int parentIndex) {
        //The method needs indexes that start from 1, not 0 like in our array, so temporarily increase the given array by 1.
        parentIndex++;
        //Normally the formula would be rc = 2 * i + 1, but since we have to -1 due to array indexing, the +1 can be let out complete.
        int rightChildIndex = (2 * parentIndex);

        if (!(rightChildIndex >= depq.size())) {
            return depq.get(rightChildIndex);
        }

        return null;
    }

    /*------------------------------------------------------------------------------------- END OF HELPER METHODS ---*/
}