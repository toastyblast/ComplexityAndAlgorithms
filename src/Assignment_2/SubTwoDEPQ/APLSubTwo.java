package Assignment_2.SubTwoDEPQ;

public class APLSubTwo {
    //Variables...

    public static void main(String[] args) {
        new APLSubTwo().run();
    }

    private void run() {
        DEPQ depq = new DEPQ();

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

        depq.put(5, 412);
        depq.removeMin();

        depq.put(45, 413);
        depq.removeMax();

        System.out.println(depq.getMin());
        System.out.println(depq.getMax());
        System.out.println(depq.simpleArrayToString());

        //Code here...
    }
}
