package Assignment_2.SubTwoDEPQ;

public class APLSubTwo {
    private DEPQ depq;

    public static void main(String[] args) {
        new APLSubTwo().run();
    }

    private void run() {
        depq = new DEPQ();

        //Should be empty at this point, besides the root node.
        System.out.println("The SMMH DEPQ is empty (should be TRUE at this point): " + depq.isEmpty());
        //Because the root node is always there, the size of an empty SMMH DEPQ should be 1.
        System.out.println("The current size of the SMMH DEPQ is (root node always present, should be of size 1 at this point): " + depq.size() + '\n');

        //Build a base SMMH DEPQ.
        depq.put(5, 400);
        depq.put(10, 401);
        depq.put(45, 402);
        depq.put(25, 403);
        depq.put(8, 405);
        depq.put(40, 406);
        depq.put(20, 407);
        depq.put(15, 408);
        depq.put(9, 409);
        depq.put(30, 410);
        depq.put(19, 411);
        //CHECK: Should be of: [ROOT, 5, 45, 8, 9, 20, 40, 10, 15, 25, 30, 19] with min = 400 & max = 402 at this first printAll().
        printAll();
        //The SMMH DEPQ isn't empty anymore.
        System.out.println("The SMMH DEPQ is empty (should be FALSE at this point): " + depq.isEmpty());
        //The size of the SMMH DEPQ is now 12 as 11 nodes have been added.
        System.out.println("The current size of the SMMH DEPQ is (root node always present, should be of size 12 at this point): " + depq.size() + '\n');

        //Should add the new node to either index 4 or 5, as it has the same priority as the min node with value 400.
        depq.put(5, 412);
        //Should remove the priority 5 node with value 400, making the priority 5 with value 412 the new min.
        depq.removeMin();
        //CHECK: Should be of: [ROOT, 5, 45, 8, 9, 20, 40, 10, 15, 25, 30, 19] with min = 412 & max = 402 at this printAll().
        printAll();

        //Should add the new node to either index 6 or 7, as it has the same priority as the max node with value 402.
        depq.put(45, 413);
        //Should remove the priority 45 node with value 402, making the priority 45 with value 413 node the new max.
        depq.removeMax();
        //CHECK: Should be of: [ROOT, 5, 45, 8, 9, 20, 40, 10, 15, 25, 30, 19] with min = 412 & max = 413 at this printAll().
        printAll();

        //Should add the new node to either index 6 or 7, as it has the same priority as the max node with value 413.
        depq.put(45, 414);
        //Should move this node to index 4 or 5 (as it now has the same priority as the min node on index 2). Counted from indexing starting at 1.
        depq.changePriority(3, 5);
        //CHECK: Should be of: [ROOT, 5, 45, 8, 9, 20, 40, 10, 15, 25, 30, 19] with min = 412 & max = 414 at this printAll().
        printAll();

        //Should remove the priority 5 node with value 412, making the new min node the node with priority 5 and value 414.
        depq.removeMin();
        //CHECK: Should be of: [ROOT, 5, 45, 8, 9, 20, 40, 10, 15, 25, 30, 19] with min = 413 & max = 414 at this printAll().
        printAll();
    }

    private void printAll() {
        System.out.println("The left child of root has a value of: " + depq.getMin());
        System.out.println("The right child of root has a value of: " + depq.getMax());
        System.out.println(depq.simpleArrayToString());
    }
}
